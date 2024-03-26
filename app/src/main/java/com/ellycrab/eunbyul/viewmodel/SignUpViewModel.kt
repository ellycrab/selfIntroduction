package com.ellycrab.eunbyul.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private val _name = MutableLiveData<String>()
    val name:LiveData<String> get() = _name

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    fun setName(value:String){
        _name.value = value
    }
    fun setId(value:String){
        _id.value = value
    }
    fun setPassword(value:String){
        _password.value = value
    }
    fun updateData(name: String, id: String, password: String) {
        _name.value = name
        _id.value = id
        _password.value = password
    }
}