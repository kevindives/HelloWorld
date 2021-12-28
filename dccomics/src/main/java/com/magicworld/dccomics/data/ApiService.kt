package com.magicworld.dccomics.data

import com.magicworld.dccomics.model.Superheroe
import retrofit2.http.GET


interface ApiService {
    @GET("kevindives/Practicandomovil/superheroes")
    suspend fun getSuperheroes(): Superheroe

}