package com.bobobox.poketest.resources.util.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loading : LiveData<Boolean> get() = _loading
    val _loading = MutableLiveData<Boolean>()

    val error_message : LiveData<String> get() = _error_message
    val _error_message = MutableLiveData<String>()
}