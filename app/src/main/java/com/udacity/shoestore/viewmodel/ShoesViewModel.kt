package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    fun shoesEmpty(): Boolean {
        return _shoesList.value == null || _shoesList.value?.isEmpty() == true
    }

    fun addShoe(shoe: Shoe) {
        if (_shoesList.value == null) {
            _shoesList.value = mutableListOf(shoe)
        } else {
            _shoesList.value?.add(shoe)
        }
    }
}