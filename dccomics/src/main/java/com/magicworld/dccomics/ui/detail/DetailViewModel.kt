package com.magicworld.dccomics.ui.detail

import androidx.lifecycle.ViewModel
import com.magicworld.dccomics.data.repository.DetailRepository
import com.magicworld.dccomics.model.SuperheroeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    val detailRepository = DetailRepository()

    fun saveInFavorites(superheroe: SuperheroeItem) {

        GlobalScope.launch (Dispatchers.IO){
            detailRepository.saveInFavorites(superheroe)
        }

    }

    fun deleteInFavorites(superheroe: SuperheroeItem) {
        GlobalScope.launch (Dispatchers.IO){
            detailRepository.deleteInFavorites(superheroe)
        }
    }
}