package com.freetime.ssmpc.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.freetime.ssmpc.data.coords.CoordinateDatabase
import com.freetime.ssmpc.data.coords.CoordinateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CoordinatesViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = CoordinateDatabase.getDatabase(application).coordinateDao()
    val allCoordinates: Flow<List<CoordinateEntity>> = dao.getAllCoordinates()

    fun addCoordinate(name: String, x: Double, y: Double, z: Double, dimension: String, description: String) {
        viewModelScope.launch {
            dao.insertCoordinate(CoordinateEntity(name = name, x = x, y = y, z = z, dimension = dimension, description = description))
        }
    }

    fun deleteCoordinate(coordinate: CoordinateEntity) {
        viewModelScope.launch {
            dao.deleteCoordinate(coordinate)
        }
    }
}
