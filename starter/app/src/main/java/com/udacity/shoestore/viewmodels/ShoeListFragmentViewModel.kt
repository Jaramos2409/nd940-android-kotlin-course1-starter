package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeListFragmentViewModel : ViewModel() {

    private val _eventNavigateToLoginScreen = MutableLiveData<Boolean>()
    val eventNavigateToLoginScreen: LiveData<Boolean>
        get() = _eventNavigateToLoginScreen

    private val _eventNavigateToShoeDetailScreen = MutableLiveData<Boolean>()
    val eventNavigateToShoeDetailScreen: LiveData<Boolean>
        get() = _eventNavigateToShoeDetailScreen

    init {
        Timber.i("ShoeListViewModel created.")
        _eventNavigateToLoginScreen.value = false
    }

    fun navigateToLoginScreen(): Boolean {
        _eventNavigateToLoginScreen.value = true
        return true
    }

    fun resetNavigationToLoginScreenStatus() {
        _eventNavigateToLoginScreen.value = false
    }

    fun navigateToShoeDetailScreen(): Boolean {
        _eventNavigateToShoeDetailScreen.value = true
        return true
    }

    fun resetNavigationToShoeDetailScreenStatus() {
        _eventNavigateToShoeDetailScreen.value = false
    }

}