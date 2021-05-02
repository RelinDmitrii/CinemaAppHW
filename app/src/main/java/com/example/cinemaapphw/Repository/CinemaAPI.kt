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

    @GET("3/movie/now_playing")
    fun getCinemaListNowPlaying(
        @Query("language") language: String = "ru-RU",
        @Query("api_key") key: String
    ): Call<JsonObject>


    @GET("3/movie/upcoming")
    fun getCinemaListUpcoming(
        @Query("language") language: String = "ru-RU",
        @Query("api_key") key: String
    ): Call<JsonObject>
}