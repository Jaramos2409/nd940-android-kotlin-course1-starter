package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class WelcomeScreenViewModel : ViewModel() {

    private val _eventNavigateToInstructionsScreen = MutableLiveData<Boolean>()
    val eventNavigateToInstructionsScreen: LiveData<Boolean>
        get() = _eventNavigateToInstructionsScreen

    init {
        Timber.i("WelcomeScreenViewModel created.")
        _eventNavigateToInstructionsScreen.value = false
    }

    fun navigateToInstructionsScreen() {
        _eventNavigateToInstructionsScreen.value = true
    }

    fun resetNavigationStatus() {
        _eventNavigateToInstructionsScreen.value = false
    }

}