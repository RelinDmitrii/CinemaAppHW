package com.example.testcinema.ui.ViewModelHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.cinemaapphw.ViewModelHome.App.Companion.getFavoritesDao
import com.example.testcinema.DataClasses.Cinema

import com.example.testcinema.Repository.Repository
import com.example.testcinema.Repository.RepositoryImpl
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep


private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class HomeViewModel(private val stateNowPlaying: MutableLiveData<AppState> = MutableLiveData(),
                    private val stateUpcoming: MutableLiveData<AppState> = MutableLiveData(),
                    private val detailRepositoryImpl: Repository = RepositoryImpl(
                        RemoteDataSource(), getFavoritesDao())
) : ViewModel() {


    fun getDataNowPlaying(): LiveData<AppState> {
        return stateNowPlaying
    }

    fun getDataUpcoming(): LiveData<AppState> {
        return stateUpcoming
    }


    private val callbackNowPlaying = object:
        Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            response.body()?.let {
                val gson = Gson()
                val arrayTutorialType = object : TypeToken<List<Cinema>>() {}.type
                var cinemaList: List<Cinema> = gson.fromJson(it.get("results"), arrayTutorialType)
//                cinemaList.filter { it.adult }
                stateNowPlaying.postValue(AppState.SuccessNowPlaying(cinemaList))
            }

        }
        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            stateNowPlaying.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    private val callbackUpcoming = object:
        Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            response.body()?.let {
                val gson = Gson()
                val arrayTutorialType = object : TypeToken<List<Cinema>>() {}.type
                var cinemaList: List<Cinema> = gson.fromJson(it.get("results"), arrayTutorialType)
                stateUpcoming.postValue(AppState.SuccessUpcoming(cinemaList))
            }

        }
        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            stateUpcoming.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    fun getDataFromRemote() {
        stateNowPlaying.value = AppState.Loading
        stateUpcoming.value = AppState.Loading
        detailRepositoryImpl.getListCinemaNowPlayingFromServer(callbackNowPlaying)
        detailRepositoryImpl.getListCinemaUpcomingFromServer(callbackUpcoming)
    }

}