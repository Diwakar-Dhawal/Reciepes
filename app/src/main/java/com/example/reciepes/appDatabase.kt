package com.example.reciepes

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class appDatabase:RoomDatabase() {
    abstract fun getDao():Dao
}