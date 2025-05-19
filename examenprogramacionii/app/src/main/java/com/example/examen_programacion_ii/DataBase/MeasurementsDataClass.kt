package com.example.examen_programacion_ii.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity and Data Class that represents both a table on the local DB
//and the structure of the Measurement object.
@Entity
data class Measurement (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val icon: Int,
    val type: String,
    val measurement: Int,
    val date: String
)
