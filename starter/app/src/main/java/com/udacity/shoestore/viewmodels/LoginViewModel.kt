package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _eventNavigateToWelcomeScreen = MutableLiveData<Boolean>()
    val eventNavigateToWelcomeScreen: LiveData<Boolean>
        get() = _eventNavigateToWelcomeScreen

    init {
        Timber.i("LoginViewModel created.")
        _eventNavigateToWelcomeScreen.value = false
    }

    fun navigateToWelcomeScreen() {
        _eventNavigateToWelcomeScreen.value = true
    }

    fun resetNavigationStatus() {
        _eventNavigateToWelcomeScreen.value = false
    }
}