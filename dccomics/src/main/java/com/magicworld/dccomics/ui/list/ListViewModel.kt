package com.magicworld.dccomics.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.magicworld.dccomics.data.repository.ListRepository
import com.magicworld.dccomics.model.Superheroe
import com.magicworld.dccomics.model.SuperheroeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream


class ListViewModel : ViewModel() {


    private val listRepository = ListRepository()

    private var superheroesLoad: MutableLiveData<ArrayList<SuperheroeItem>> = MutableLiveData()
    val onSuperheroesLoaded: LiveData<ArrayList<SuperheroeItem>> = superheroesLoad


    private var userLogin : MutableLiveData<Boolean> = MutableLiveData()
    val onUserLoggedIn: LiveData<Boolean> = userLogin



    fun getSuperheroesFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            superheroesLoad.postValue(listRepository.getSuperheroes())
        }
    }

    fun loadMockSuperHeroesFromJson(superHeroeString: InputStream?) {
        val superHeroesString: String = superHeroeString?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        superheroesLoad.value = gson.fromJson(superHeroesString, Superheroe::class.java)

    }
    fun checkUserConnected() {
        userLogin.value =listRepository.checkUserConnected()

    }

    fun getSuperheroesFromFireBase() {
        GlobalScope.launch (Dispatchers.IO){
            superheroesLoad.postValue(listRepository.getSuperheroesFromFireBase())
        }
    }
}