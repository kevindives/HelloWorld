package com.magicworld.dccomics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuperheroeLocal::class], version = 1)
abstract class SuperheroeDatabase : RoomDatabase(){

    abstract fun SuperheroeDao(): SuperheroeDao
}