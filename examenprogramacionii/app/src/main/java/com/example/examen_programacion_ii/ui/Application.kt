package com.example.examen_programacion_ii.ui

import android.app.Application
import androidx.room.Room
import com.example.examen_programacion_ii.DataBase.MeasurementsDB


//Overwrites Application to handle dependencies for ViewModel.
class Aplicacion: Application() {

    val m_db by lazy {
        Room.databaseBuilder(this, MeasurementsDB::class.java, "MeasurementsDB").build()
    }

    val m_dao by lazy {m_db.measurementsDAO()}
}
