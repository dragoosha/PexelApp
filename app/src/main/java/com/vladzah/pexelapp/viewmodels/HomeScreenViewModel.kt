package com.vladzah.pexelapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.vladzah.model.PhotoModel
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.GetPhotosUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPhotosUsecase: GetPhotosUsecase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _photos = MutableStateFlow<PagingData<PhotoUiModel>>(PagingData.empty())
    val photos: StateFlow<PagingData<PhotoUiModel>> get() = _photos

    init {
        observePhotos()
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
