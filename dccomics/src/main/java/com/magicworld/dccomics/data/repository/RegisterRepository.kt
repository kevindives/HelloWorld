package com.magicworld.dccomics.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.dccomics.model.User
import kotlinx.coroutines.tasks.await

class RegisterRepository {
    private lateinit var  auth: FirebaseAuth

    suspend fun createUser(email: String, password: String): String {

        return  try {
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password).await()
            "Usuario registrado exitosamente"
        }catch (e: FirebaseAuthException){
            return e.message.toString()
        }
    }

    suspend fun createUserInDataBase(email: String, username: String): String {

        val db= Firebase.firestore
        return try {
            val idUser = db.collection("user").document()
            val user = User(id = idUser.id, email = email, username = username)

            db.collection("user")
                .document(idUser.id)
                .set(user)
                .await()
            "Usuario creado de forma exitosa"
        }catch (e: FirebaseAuthException){
            return e.message.toString()
        }
    }

}