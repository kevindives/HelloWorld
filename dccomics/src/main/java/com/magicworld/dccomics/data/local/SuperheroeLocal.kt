package com.magicworld.dccomics.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_superheroe")
data class SuperheroeLocal (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "alias") val alias: String = "",
    @ColumnInfo(name ="altura" ) val altura: Double = 0.0,
    @ColumnInfo(name = "city" ) val city: String = "",
    @ColumnInfo(name = "facebook" ) val facebook: String = "",
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lng") val lng: Double = 0.0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "occupation") val occupation: String = "",
    @ColumnInfo(name = "powers") val powers: String = "",
    @ColumnInfo(name = "urlPIcture" ) val urlPicture: String = "",
    @ColumnInfo(name = "zoom") val zoom: Float = 0.0f
)