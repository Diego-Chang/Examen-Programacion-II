package com.example.examen_programacion_ii.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

//Data Base
@Database(entities = [Measurement::class], version = 1)
abstract class MeasurementsDB: RoomDatabase() {
    abstract fun measurementsDAO(): MeasurementsDAO
}
