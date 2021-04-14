package com.example.testcinema.Repository

import com.example.testcinema.DataClasses.Cinema
import com.google.gson.JsonObject
import retrofit2.Callback

interface Repository {

    fun getCinemaFromServer(
        id: Int,
        callback: Callback<Cinema>
    )

    fun getCinemaFromLocalStorage(): Cinema

    fun getListCinemaFromServer(
        callback: Callback<JsonObject>
    )


}