package com.example.testcinema.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidwithkotlin.room.FavoriteDao
import com.example.androidwithkotlin.room.FavoriteEntity
import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.testcinema.DataClasses.Cinema
import com.google.gson.JsonObject
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource, private val localDataSource: FavoriteDao) : Repository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getCinemaFromServer(
        id: Int,
        callback: Callback<Cinema>
    ) {
        remoteDataSource.getCinemaDetails(id, callback)
    }

    override fun getCinemaFromLocalStorage(): List<FavoriteEntity> {
        return localDataSource.all()
    }


    override fun saveCinemaToFavorites(cinema: Cinema) {
        localDataSource.insert(FavoriteEntity(cinema.id, cinema.title))
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListCinemaNowPlayingFromServer(callback: Callback<JsonObject>) {
        remoteDataSource.getListCinemaNowPlaying(callback)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListCinemaUpcomingFromServer(callback: Callback<JsonObject>) {
        remoteDataSource.getListCinemaUpcoming(callback)
    }
}