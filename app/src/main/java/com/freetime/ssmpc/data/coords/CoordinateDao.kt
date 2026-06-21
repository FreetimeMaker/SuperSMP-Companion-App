package com.freetime.ssmpc.data.coords

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CoordinateDao {
    @Query("SELECT * FROM coordinates ORDER BY name ASC")
    fun getAllCoordinates(): Flow<List<CoordinateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinate(coordinate: CoordinateEntity)

    @Delete
    suspend fun deleteCoordinate(coordinate: CoordinateEntity)

    @Update
    suspend fun updateCoordinate(coordinate: CoordinateEntity)
}
