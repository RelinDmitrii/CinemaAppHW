package com.example.testcinema.ui.ViewModelHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.testcinema.DataClasses.Cinema
import com.example.testcinema.DataClasses.getNowPlayingList
import com.example.testcinema.DataClasses.getUpComingList
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

class HomeViewModel(
    private val state: MutableLiveData<AppState> = MutableLiveData(),
    private val detailRepositoryImpl: Repository = RepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {

    // Для заглушки 2 списка
    private val list1 = listOf<Cinema>()
    private val list2 = listOf<Cinema>()


    fun getData(): LiveData<AppState> {
        return state
    }


    private val callback = object :
        Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            response.body()?.let {
                val gson = Gson()
                val arrayTutorialType = object : TypeToken<List<Cinema>>() {}.type
                var cinemaList: List<Cinema> = gson.fromJson(it.get("results"), arrayTutorialType)
                state.postValue(AppState.Success(cinemaList))
            }

        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            state.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }

    fun getDataFromRemote() {
        state.value = AppState.Loading
        detailRepositoryImpl.getListCinemaFromServer(callback)
    }


}