package com.vladzah.pexelapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.getUsecases.GetBookmarksUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarksScreenViewModel @Inject constructor(
    private val getBookmarksUsecase: GetBookmarksUsecase
): ViewModel(){
    private val _bookmarks = MutableStateFlow<PagingData<PhotoUiModel>>(PagingData.empty())
    val bookmars: StateFlow<PagingData<PhotoUiModel>> = _bookmarks

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch {
            getBookmarksUsecase.execute()
                .map { pagingData ->
                    pagingData.map { photoModel ->
                        photoModel.toUiModel()
                    }
                }
                .cachedIn(viewModelScope)
                .collectLatest { transformedData ->
                    _bookmarks.value = transformedData }
        }
    }
}
