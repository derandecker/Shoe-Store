package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class SharedShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = ArrayList<Shoe>()
    }

    fun updateShoeList(
        name: String, size: Double,
        company: String, description: String
    ) {
        val shoe = Shoe(name, size, company, description)
         _shoeList.value?.add(shoe)
    }

}