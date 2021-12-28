package com.magicworld.dccomics.data.repository

import com.magicworld.dccomics.HelloWorld
import com.magicworld.dccomics.data.local.SuperheroeDao
import com.magicworld.dccomics.data.local.SuperheroeLocal
import com.magicworld.dccomics.model.SuperheroeItem
import java.sql.Types

class DetailRepository {

    fun saveInFavorites(superheroe: SuperheroeItem) {

        val superheroeLocal = SuperheroeLocal(
            id = Types.NULL,
            name = superheroe.name,
            alias = superheroe.alias,
            city = superheroe.city,
            facebook = superheroe.facebook,
            altura = superheroe.altura,
            occupation = superheroe.occupation,
            powers = superheroe.powers,
            lat = superheroe.lat,
            lng = superheroe.lng,
            zoom = superheroe.zoom,
            urlPicture = superheroe.urlPicture
        )

        val superheroeDao: SuperheroeDao = HelloWorld.database.SuperheroeDao()
        superheroeDao.createSuperheroe(superheroeLocal)
    }

    fun deleteInFavorites(superheroe: SuperheroeItem) {
        val superheroeLocal = SuperheroeLocal(
            id = Types.NULL,
            name = superheroe.name,
            alias = superheroe.alias,
            city = superheroe.city,
            facebook = superheroe.facebook,
            altura = superheroe.altura,
            occupation = superheroe.occupation,
            powers = superheroe.powers,
            lat = superheroe.lat,
            lng = superheroe.lng,
            zoom = superheroe.zoom,
            urlPicture = superheroe.urlPicture
        )
        val superheroeDao: SuperheroeDao = HelloWorld.database.SuperheroeDao()
        superheroeDao.deleteSupereheroe(superheroeLocal)
    }
}