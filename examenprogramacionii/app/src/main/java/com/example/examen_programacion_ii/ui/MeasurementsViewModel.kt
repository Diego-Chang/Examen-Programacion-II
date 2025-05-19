package com.example.examen_programacion_ii.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.examen_programacion_ii.DataBase.Measurement
import com.example.examen_programacion_ii.DataBase.MeasurementsDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ViewModel for local DB functions access.
class MeasurementsViewModel(private val measurementsDAO: MeasurementsDAO): ViewModel() {

    //Saves list of Measurement from local DB.
    var measurements by mutableStateOf(listOf<Measurement>())


    //Insert to Measurement to local DB.
    fun insertDB(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            measurementsDAO.InsertMeasurement(measurement)
            getDB()
        }
    }

    //Get all items from local DB ordered by date, desc.
    fun getDB(): List<Measurement> {
        viewModelScope.launch(Dispatchers.IO) {
            measurements = measurementsDAO.GetMeasurementsByDateDESC()
        }
        return measurements
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val aplicacion = (this[APPLICATION_KEY] as Aplicacion)
                MeasurementsViewModel(aplicacion.m_dao)
            }
        }
    }
}
