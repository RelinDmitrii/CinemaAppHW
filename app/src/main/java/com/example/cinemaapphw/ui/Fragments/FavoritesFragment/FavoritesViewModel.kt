package com.example.cinemaapphw.ui.Fragments.FavoritesFragment


import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.cinemaapphw.ViewModelHome.App.Companion.getFavoritesDao

import com.example.testcinema.Repository.Repository
import com.example.testcinema.Repository.RepositoryImpl

import com.example.testcinema.ui.ViewModelHome.AppState


class FavoritesViewModel(
    val state: MutableLiveData<AppState> = MutableLiveData(),
    private val FavoritesRepositoryImpl: Repository = RepositoryImpl(
        RemoteDataSource(),
        getFavoritesDao()
    )
) : ViewModel() {

    fun getAllFavorites() {
        state.value = AppState.Loading
        val handler = Handler(Looper.getMainLooper())
        Thread {
            val favoritesList = FavoritesRepositoryImpl.getCinemaFromLocalStorage()
            handler.post(Runnable {
                state.value = AppState.SuccessFavorites(favoritesList)
            })
        }.start()

    }

}




