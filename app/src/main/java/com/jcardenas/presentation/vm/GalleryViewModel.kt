package com.jcardenas.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Image
import com.jcardenas.domain.usecases.ListAllImageUseCase
import com.jcardenas.domain.usecases.SaveImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val saveImageUseCase: SaveImageUseCase,
    private val listAllImageUseCase: ListAllImageUseCase
): ViewModel() {

    val loading = MutableLiveData(false)
    val data = MutableLiveData<ArrayList<Image>>(arrayListOf())
    val error = MutableLiveData(false)

    fun uploadImage(image: ByteArray, extension: String){
        viewModelScope.launch {
            loading.postValue(true)
            error.postValue(false)

            val flowResult = saveImageUseCase.upload(image, extension)
            flowResult.collect { result ->
                when(result) {
                    is Result.Success -> {
                        loading.postValue(false)
                        data.value?.add(result.data)
                        data.postValue(data.value)
                    }
                    else -> {
                        loading.postValue(false)
                        error.postValue(true)
                    }
                }
            }
        }
    }

    fun listAll() {
        viewModelScope.launch {
            loading.postValue(true)
            error.postValue(false)
            val flowResult = listAllImageUseCase.listAll()
            flowResult.collect { result ->
                when(result){
                    is Result.Success -> {
                        loading.postValue(false)
                        data.postValue(ArrayList(result.data))
                    }
                    else -> {
                        loading.postValue(false)
                        error.postValue(true)
                    }
                }
            }
        }
    }

}