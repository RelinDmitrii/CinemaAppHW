package com.example.testcinema.Repository

import com.example.androidwithkotlin.room.FavoriteEntity
import com.example.testcinema.DataClasses.Cinema
import com.google.gson.JsonObject
import retrofit2.Callback

interface Repository {

    fun getCinemaFromServer(
        id: Int,
        callback: Callback<Cinema>
    )

    fun getCinemaFromLocalStorage(): List<FavoriteEntity>

    fun saveCinemaToFavorites(cinema: Cinema)

    fun getListCinemaNowPlayingFromServer(
        callback: Callback<JsonObject>
    )

    fun getListCinemaUpcomingFromServer(
        callback: Callback<JsonObject>
    )




}