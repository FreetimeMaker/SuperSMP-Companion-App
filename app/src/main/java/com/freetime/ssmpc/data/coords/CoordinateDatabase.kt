package com.freetime.ssmpc.data.coords

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoordinateEntity::class], version = 1, exportSchema = false)
abstract class CoordinateDatabase : RoomDatabase() {
    abstract fun coordinateDao(): CoordinateDao

    companion object {
        @Volatile
        private var INSTANCE: CoordinateDatabase? = null

        fun getDatabase(context: Context): CoordinateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoordinateDatabase::class.java,
                    "coordinate_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
