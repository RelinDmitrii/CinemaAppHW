package com.example.cinemaapphw.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cinemaapphw.Support.API
import com.example.testcinema.DataClasses.Cinema
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {

    private val cinemaAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
//        .client(createOkHttpClient(WeatherApiInterceptor()))
        .build()
        .create(CinemaAPI::class.java)

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCinemaDetails(id: Int, callback: Callback<Cinema>) {
        cinemaAPI.getCinema(id, key = API).enqueue(callback)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getListCinemaNowPlaying(callback: Callback<JsonObject>) {
        cinemaAPI.getCinemaListNowPlaying(key = API).enqueue(callback)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getListCinemaUpcoming(callback: Callback<JsonObject>) {
        cinemaAPI.getCinemaListUpcoming(key = API).enqueue(callback)
    }


//    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(interceptor)
//        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        return httpClient.build()
//    }
//
//    inner class WeatherApiInterceptor : Interceptor {
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            return chain.proceed(chain.request())
//        }
//    }
}