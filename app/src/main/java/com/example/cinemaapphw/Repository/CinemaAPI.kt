package com.example.cinemaapphw.Repository

import com.example.testcinema.DataClasses.Cinema
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemaAPI {

    @GET("3/movie/{id}")
    fun getCinema(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): Call<Cinema>

    @GET("3/discover/movie")
    fun getCinemaList(
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("api_key") key: String
    ): Call<JsonObject>
}