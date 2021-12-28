package com.magicworld.dccomics

import android.app.Application
import androidx.room.Room
import com.magicworld.dccomics.data.local.SuperheroeDatabase

class HelloWorld : Application() {

    companion object{
        lateinit var database: SuperheroeDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            SuperheroeDatabase::class.java,
            "superheroe_db"
        ).build()
    }
}