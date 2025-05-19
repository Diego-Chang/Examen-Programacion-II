package com.example.examen_programacion_ii.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


//Data Access Object for local DB. Defines CRUD operations.
@Dao
interface MeasurementsDAO {

    @Query("SELECT * FROM Measurement ORDER BY date DESC")
    suspend fun GetMeasurementsByDateDESC(): List<Measurement>

    @Insert
    suspend fun InsertMeasurement (measurement: Measurement)

    @Update
    suspend fun UpdateMeasurement (measurement: Measurement)

    @Delete
    suspend fun DeleteMeasurement (measurement: Measurement)

}
