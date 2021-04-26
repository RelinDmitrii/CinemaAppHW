package com.example.androidwithkotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteEntity::class), version = 1, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}
