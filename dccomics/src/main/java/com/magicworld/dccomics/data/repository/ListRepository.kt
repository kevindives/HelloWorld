package com.magicworld.dccomics.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.magicworld.dccomics.data.ApiFactory
import com.magicworld.dccomics.model.SuperheroeItem
import kotlinx.coroutines.tasks.await

class ListRepository {

    private lateinit var auth: FirebaseAuth

    suspend fun getSuperheroes() = ApiFactory.retrofit.getSuperheroes()


    fun checkUserConnected(): Boolean {

        auth = Firebase.auth
        val currentUser = auth.currentUser
        return  currentUser != null

    }

    suspend fun getSuperheroesFromFireBase(): ArrayList<SuperheroeItem> {
        val db = Firebase.firestore

        val result = db.collection("superheroes").get().await()

        val listSuperheroes: ArrayList<SuperheroeItem> = arrayListOf()
        for (superheroe in result)
            listSuperheroes.add(superheroe.toObject<SuperheroeItem>())

        return listSuperheroes
    }
}