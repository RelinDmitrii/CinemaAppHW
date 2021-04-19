package com.example.cinemaapphw.ViewModelHome

import android.app.Application
import androidx.room.Room
import com.example.androidwithkotlin.room.FavoriteDao
import com.example.androidwithkotlin.room.FavoriteDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: FavoriteDataBase? = null
        private const val DB_NAME = "Favorite.db"

        fun getFavoritesDao(): FavoriteDao {
            if (db == null){
                synchronized(FavoriteDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FavoriteDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.favoriteDao()
        }
    }
}