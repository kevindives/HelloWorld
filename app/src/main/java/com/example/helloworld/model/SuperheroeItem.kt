package com.example.helloworld.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperheroeItem(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("altura")
    val altura: Double,
    @SerializedName("city")
    val city: String,
    @SerializedName("facebook")
    val facebook: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("occupation")
    val occupation: String,
    @SerializedName("powers")
    val powers: String,
    @SerializedName("urlPicture")
    val urlPicture: String
):Serializable