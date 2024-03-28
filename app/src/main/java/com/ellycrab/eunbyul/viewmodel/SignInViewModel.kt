package com.ellycrab.eunbyul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel: ViewModel()  {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun updateLoginData(id: String, password: String) {

        _id.value = id
        _password.value = password
    }
}