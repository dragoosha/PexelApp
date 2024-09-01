package com.vladzah.pexelapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vladzah.pexelapp.events.HomeScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.TopicUiModel
import com.vladzah.pexelapp.models.toTopicUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.getUsecases.GetPhotosUsecase
import com.vladzah.usecases.getUsecases.GetTitleUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPhotosUsecase: GetPhotosUsecase,
    private val getTitleUsecase: GetTitleUsecase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _photos = MutableStateFlow<PagingData<PhotoUiModel>>(PagingData.empty())
    val photos: StateFlow<PagingData<PhotoUiModel>> get() = _photos

    private val _titles = MutableStateFlow<List<TopicUiModel>>(emptyList())
    val titles: StateFlow<List<TopicUiModel>> = _titles

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var originalTitles = listOf<TopicUiModel>()


    init {
        observePhotos()
        observeTitles()
    }

    private fun observeTitles() {
        viewModelScope.launch {
            val list = getTitleUsecase.execute().map {
                it.toTopicUiModel()
            }
            _titles.value = list
        }
    }

    private fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun checkAndMoveTitle(query: String) {
        val updatedTitles = _titles.value.map { title ->
            if (title.label == query) {
                title.copy(isSelected = true)
            } else {
                title.copy(isSelected = false)
            }
        }

        _titles.value = updatedTitles
        changeSelectedTitlePosition()
    }

    private fun changeSelectedTitlePosition() {
        val currentTitles = _titles.value.toMutableList()
        val selectedTitle = currentTitles.find { it.isSelected }

        if (selectedTitle != null) {
            currentTitles.remove(selectedTitle)
            currentTitles.add(0, selectedTitle)
        } else {
            currentTitles.clear()
            currentTitles.addAll(originalTitles)
        }

        _titles.value = currentTitles
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observePhotos() {
        viewModelScope.launch {
            query
                .flatMapLatest { queryValue ->
                    getPhotosUsecase.execute(queryValue)
                        .map { pagingData ->
                            Log.d("MainViewModel" , "$pagingData")
                            pagingData.map { photoModel ->
                                photoModel.toUiModel()
                            }
                        }
                        .cachedIn(viewModelScope)
                }
                .collectLatest { transformedPagingData ->
                    _photos.value = transformedPagingData
                }
        }

        updateIsLoading()
    }

    private fun updateIsLoading() {
        _isLoading.value = false
    }

    fun onEvent(event: HomeScreenEvents) {
        when(event){
            is HomeScreenEvents.onNewQuery -> setQuery(event.query)
            is HomeScreenEvents.onExploreClicked -> observePhotos()
            is HomeScreenEvents.onRetryClicked -> observePhotos()
            else -> {}
        }
    }

}
