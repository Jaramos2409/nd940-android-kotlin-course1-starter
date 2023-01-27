package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ListOfShoesViewModel : ViewModel() {

    private val _listOfShoes = MutableLiveData<MutableList<Shoe>>()
    val listOfShoes: LiveData<MutableList<Shoe>>
        get() = _listOfShoes

    init {
        Timber.i("ListOfShoesViewModel created.")
        _listOfShoes.value = mutableListOf()
    }

    fun addShoeToList(shoe: Shoe) {
        _listOfShoes.value?.add(shoe)
    }

}