package com.example.cinemaapphw.ui.Fragments.DetailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapphw.Repository.RemoteDataSource
import com.example.cinemaapphw.ViewModelHome.App.Companion.getFavoritesDao
import com.example.testcinema.DataClasses.Cinema
import com.example.testcinema.Repository.Repository
import com.example.testcinema.Repository.RepositoryImpl
import com.example.testcinema.ui.ViewModelHome.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class DetailsViewModel (private val state: MutableLiveData<AppState> = MutableLiveData(),
                        private val detailRepositoryImpl: Repository = RepositoryImpl(
                            RemoteDataSource(),
                            getFavoritesDao()
                        )
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = state

    fun getCinemaFromRemoteSource(id: Int) {
        state.value = AppState.Loading
        detailRepositoryImpl.getCinemaFromServer(id, callback)
    }

    private val callback = object:
        Callback<Cinema> {
        override fun onResponse(call: Call<Cinema>, response: Response<Cinema>) {
            response.body()?.let {
                state.postValue(AppState.SuccessDetails(it))
            }

        }

        override fun onFailure(call: Call<Cinema>, t: Throwable) {
            state.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

    }


    fun addToFavorites(){
        val appstate = state.value as AppState.SuccessDetails
        detailRepositoryImpl.saveCinemaToFavorites(appstate.cinema)
    }

}