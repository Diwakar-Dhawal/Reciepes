package com.example.reciepes

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.room.Dao
import androidx.room.Query


@Dao
interface Dao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe?>
}
