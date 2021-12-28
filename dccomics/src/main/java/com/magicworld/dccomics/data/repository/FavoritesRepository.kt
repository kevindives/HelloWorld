package com.magicworld.dccomics.data.repository

import com.magicworld.dccomics.HelloWorld
import com.magicworld.dccomics.data.local.SuperheroeDao
import com.magicworld.dccomics.data.local.SuperheroeLocal

class FavoritesRepository {
    fun getFavoritesSuperheroe() : MutableList<SuperheroeLocal>?{
        val superheroeDao: SuperheroeDao = HelloWorld.database.SuperheroeDao()
        return superheroeDao.getFavoritesSuperheroes()
    }
}