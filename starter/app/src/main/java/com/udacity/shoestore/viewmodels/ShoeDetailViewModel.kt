package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {

    private val _eventCancelAndNavigateToShoeListScreen = MutableLiveData<Boolean>()
    val eventCancelAndNavigateToShoeListScreen: LiveData<Boolean>
        get() = _eventCancelAndNavigateToShoeListScreen

    private val _eventSaveAndNavigateToShoeListScreen = MutableLiveData<Boolean>()
    val eventSaveAndNavigateToShoeListScreen: LiveData<Boolean>
        get() = _eventSaveAndNavigateToShoeListScreen

    val shoeName = MutableLiveData<String>()

    val shoeCompany = MutableLiveData<String>()

    val shoeSize = MutableLiveData<Double>()

    val shoeDescription = MutableLiveData<String>()

    init {
        Timber.i("ShoeDetailViewModel created")
        _eventCancelAndNavigateToShoeListScreen.value = false
        _eventSaveAndNavigateToShoeListScreen.value = false
    }

    fun cancelAndNavigateToShoeListScreen() {
        _eventCancelAndNavigateToShoeListScreen.value = true
    }

    fun resetCancelNavigationStatus() {
        _eventCancelAndNavigateToShoeListScreen.value = false
    }

    fun saveAndNavigateToShoeListScreen() {
        _eventSaveAndNavigateToShoeListScreen.value = true
    }

    fun resetSaveNavigationStatus() {
        _eventSaveAndNavigateToShoeListScreen.value = false
    }

    fun getShoeDetailsInsideOfShoeModel(): Shoe {
        return Shoe(
            shoeName.value ?: "",
            shoeSize.value ?: 0.0,
            shoeCompany.value ?: "",
            shoeDescription.value ?: ""
        )
    }
}