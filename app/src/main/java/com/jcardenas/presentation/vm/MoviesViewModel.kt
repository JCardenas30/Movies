package com.jcardenas.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Movie
import com.jcardenas.domain.usecases.GetMovieUseCase
import com.jcardenas.domain.usecases.InsertMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val insertMovieUseCase: InsertMovieUseCase
): ViewModel() {

    val loading = MutableLiveData(true)
    val error = MutableLiveData(false)
    val data = MutableLiveData<List<Movie>>(listOf())

    fun getMovies(){
        viewModelScope.launch {
            loading.postValue(true)
            error.postValue(false)
            val localResult = getMovieUseCase.local()
            localResult.collect { list ->
                if(list.isEmpty()) {
                    when(val result = getMovieUseCase.remote()){
                        is Result.Success -> {
                            insertMovieUseCase.insert(result.data)
                            loading.postValue(false)
                            data.postValue(result.data.toList())
                        }
                        is Result.Error -> {
                            loading.postValue(false)
                            data.postValue(emptyList())
                            error.postValue(true)
                        }
                    }
                } else {
                    loading.postValue(false)
                    data.postValue(list)
                }
            }
        }
    }
}