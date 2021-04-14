package com.example.testcinema.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.testcinema.DataClasses.Cinema
import com.google.gson.JsonObject
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getCinemaFromServer(
        id: Int,
        callback: Callback<Cinema>
    ) {
        remoteDataSource.getCinemaDetails(id, callback)
    }

    override fun getCinemaFromLocalStorage(): Cinema {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListCinemaFromServer(callback: Callback<JsonObject>) {
        remoteDataSource.getListCinema(callback)
    }
}