package com.jcardenas.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcardenas.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {

    fun all() = getUserUseCase.all()
}