package com.vladzah.pexelapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladzah.pexelapp.events.DetailedScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.DownloadImageUsecase
import com.vladzah.usecases.ToggleBookmarkStatusUseCase
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromApiUsecase
import com.vladzah.usecases.getUsecases.GetPhotoByIdFromDbUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getPhotoByIdFromDbUsecase: GetPhotoByIdFromDbUsecase,
    private val getPhotoByIdFromApiUsecase: GetPhotoByIdFromApiUsecase,
    private val downloadImageUseCase: DownloadImageUsecase,
    private val toggleBookmarkStatusUseCase: ToggleBookmarkStatusUseCase
) : ViewModel() {

    private val _photoModel = MutableStateFlow<PhotoUiModel?>(null)
    val photoModel: StateFlow<PhotoUiModel?> = _photoModel

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun initDataFromDb(id: Int) {
        viewModelScope.launch {
            getPhotoByIdFromDbUsecase.execute(id).collect { data ->
                _photoModel.value = data.toUiModel()
                _isLoading.value = false
            }
        }
    }

    fun initDataFromApi(id: Int) {
        viewModelScope.launch {
            getPhotoByIdFromApiUsecase.execute(id).collect { data ->
                _photoModel.value = data.toUiModel()
                _isLoading.value = false
            }
        }
    }

    private fun downloadPhoto(photo: PhotoUiModel) {
        viewModelScope.launch {
            downloadImageUseCase.execute(photo.url, photo.id)
        }
    }

    private fun toggleBookmarkStatus(photo: PhotoUiModel) {
        viewModelScope.launch {
            toggleBookmarkStatusUseCase.execute(photo.id)
        }
    }


    fun onEvent(event: DetailedScreenEvents){
        when(event) {
            is DetailedScreenEvents.onInitFromHomeEvent -> {
                initDataFromApi(event.id)
            }
            is DetailedScreenEvents.onInitFromBookmarkEvent -> {
                initDataFromDb(event.id)
            }
            is DetailedScreenEvents.onDowloadClickEvent -> {
                downloadPhoto(event.photoUiModel)
            }
            is DetailedScreenEvents.onBookmarkClickEvent -> {
                toggleBookmarkStatus(event.photoUiModel)
            }

        }
    }
}