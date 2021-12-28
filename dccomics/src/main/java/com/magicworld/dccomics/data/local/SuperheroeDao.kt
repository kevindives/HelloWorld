package com.magicworld.dccomics.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuperheroeDao {

    @Insert
    fun createSuperheroe(superheroeLocal: SuperheroeLocal)

    @Query("SElECT * FROM table_superheroe")
    fun getFavoritesSuperheroes(): MutableList<SuperheroeLocal>

    @Delete
    fun deleteSupereheroe(superheroe: SuperheroeLocal)

}