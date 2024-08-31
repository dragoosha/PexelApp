package com.vladzah.pexelapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladzah.pexelapp.events.DetailedScreenEvents
import com.vladzah.pexelapp.models.PhotoUiModel
import com.vladzah.pexelapp.models.toUiModel
import com.vladzah.usecases.GetPhotoByIdFromApiUsecase
import com.vladzah.usecases.GetPhotoByIdFromDbUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getPhotoByIdFromDbUsecase: GetPhotoByIdFromDbUsecase,
    private val getPhotoByIdFromApiUsecase: GetPhotoByIdFromApiUsecase
) : ViewModel() {

    private val _photoModel = MutableStateFlow<PhotoUiModel?>(null)
    val photoModel: StateFlow<PhotoUiModel?> = _photoModel

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id


    fun initDataFromDb(id: Int) {
        viewModelScope.launch {
            getPhotoByIdFromDbUsecase.execute(id).collect { data ->
                _photoModel.value = data.toUiModel()
            }
        }
    }

    fun initDataFromApi(id: Int) {
        viewModelScope.launch {
            getPhotoByIdFromApiUsecase.execute(id).collect { data ->
                _photoModel.value = data.toUiModel()
            }
        }
    }

    private fun setId(newId: Int) {
        _id.value = newId
    }

    fun onEvent(event: DetailedScreenEvents){
        when(event) {
            is DetailedScreenEvents.onInitFromHomeEvent -> {
                initDataFromApi(event.id)
            }
            is DetailedScreenEvents.onInitFromBookmarkEvent -> {
                initDataFromDb(event.id)
            }

        }
    }
}