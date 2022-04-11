package com.jcardenas.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Location
import com.jcardenas.domain.usecases.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
): ViewModel() {

    val data = MutableLiveData<List<Location>?>()
    val error = MutableLiveData(false)

    fun getLocations() {
        viewModelScope.launch {
            getLocationUseCase.getLocations().collect { result ->
                when(result){
                    is Result.Success -> {
                        data.postValue(result.data)
                    }
                    else -> {
                        data.postValue(emptyList())
                        error.postValue(true)
                    }
                }
            }
        }
    }

}