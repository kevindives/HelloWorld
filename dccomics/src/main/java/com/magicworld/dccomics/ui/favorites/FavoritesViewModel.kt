package com.magicworld.dccomics.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicworld.dccomics.data.local.SuperheroeLocal
import com.magicworld.dccomics.data.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private var superheroeLoad : MutableLiveData<MutableList<SuperheroeLocal>> = MutableLiveData()
    val onSuperheroeLoaded: LiveData<MutableList<SuperheroeLocal>> = superheroeLoad

    private val favoritesRepository = FavoritesRepository()
    fun getFavoritesSuperheroe() {
        GlobalScope.launch (Dispatchers.IO){
            superheroeLoad.postValue(favoritesRepository.getFavoritesSuperheroe())
        }
    }

}