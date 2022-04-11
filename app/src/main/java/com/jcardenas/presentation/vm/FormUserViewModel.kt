package com.jcardenas.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.User
import com.jcardenas.domain.usecases.InsertUserUseCase
import com.jcardenas.domain.usecases.UploadProfileUseCase
import com.jcardenas.presentation.helpers.UserFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormUserViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val uploadProfileUseCase: UploadProfileUseCase
): ViewModel() {

    val inputsErros = MutableLiveData<List<UserFields>>(listOf())
    val user = MutableLiveData<User>()
    val loading = MutableLiveData(false)
    val success = MutableLiveData<Boolean?>(null)

    fun isValid(user: User): Boolean{
        val errors = mutableListOf<UserFields>()
        if(user.employeeNumber.trim().isEmpty())
            errors.add(UserFields.employeeNumber)
        if(user.fullName.trim().isEmpty())
            errors.add(UserFields.fullName)
        if(user.address.trim().isEmpty())
            errors.add(UserFields.address)
        if(user.email.trim().isEmpty())
            errors.add(UserFields.email)
        if(user.phone.trim().isEmpty())
            errors.add(UserFields.phone)
        inputsErros.postValue(errors)
        return errors.isEmpty()
    }

    fun insertUsert(image: ByteArray?, user: User){
        viewModelScope.launch {
            loading.postValue(true)
            success.postValue(null)

            insertUserUseCase.insert(user)

            if(image != null) {
                val flowResult = uploadProfileUseCase.upload(image, user)
                flowResult.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            loading.postValue(false)
                            success.postValue(true)
                        }
                        else -> {
                            loading.postValue(false)
                            success.postValue(false)
                        }
                    }
                }
            } else {
                loading.postValue(false)
                success.postValue(true)
            }
        }
    }

}