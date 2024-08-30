package com.vladzah.pexelapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.vladzah.model.PhotoModel
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.TopicUiModel
import com.vladzah.pexelapp.models.toTopicUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.GetPhotosUsecase
import com.vladzah.usecases.GetTitleUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
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

    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    private fun observePhotos() {
        viewModelScope.launch {
            query.collectLatest { queryValue ->
                getPhotosUsecase.execute(queryValue)
                    .map { pagingData ->
                        pagingData.map { photoModel ->
                            photoModel.toUiModel()
                        }
                    }
                    .collectLatest { transformedPagingData ->
                        _photos.value = transformedPagingData
                    }
            }
        }
    }

}
