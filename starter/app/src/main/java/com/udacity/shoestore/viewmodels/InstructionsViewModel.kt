package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class InstructionsViewModel : ViewModel() {

    private val _eventNavigateToShoeListScreen = MutableLiveData<Boolean>()
    val eventNavigateToShoeListScreen: LiveData<Boolean>
        get() = _eventNavigateToShoeListScreen

    init {
        Timber.i("InstructionsViewModel created.")
        _eventNavigateToShoeListScreen.value = false
    }

    fun navigateToShoeListScreen() {
        _eventNavigateToShoeListScreen.value = true
    }

    fun resetNavigationStatus() {
        _eventNavigateToShoeListScreen.value = false
    }

}