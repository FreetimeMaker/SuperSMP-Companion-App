package com.freetime.ssmpc.data.coords

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coordinates")
data class CoordinateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val dimension: String = "Overworld",
    val description: String = ""
)
