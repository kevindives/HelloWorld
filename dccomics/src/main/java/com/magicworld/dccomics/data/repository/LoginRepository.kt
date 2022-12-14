package com.magicworld.dccomics.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class LoginRepository {

    private lateinit var auth: FirebaseAuth

    suspend fun signInUser(email: String, password: String):String {

        return try {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password).await()
            "Bienvenido"
        }catch (e:FirebaseAuthException){
            return e.message.toString()
        }
    }
}