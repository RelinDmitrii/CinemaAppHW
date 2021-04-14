package com.example.testcinema.ui.ViewModelHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcinema.DataClasses.Cinema
import com.example.testcinema.DataClasses.getNowPlayingList
import com.example.testcinema.DataClasses.getUpComingList
import com.example.testcinema.Repository.Repository
import com.example.testcinema.Repository.RepositoryImpl
import java.lang.Thread.sleep

class HomeViewModel (private val state: MutableLiveData<AppState> = MutableLiveData(),
                     private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    // Для заглушки 2 списка
    private val list1 =  listOf<Cinema>()
    private val list2 =  listOf<Cinema>()


    fun getData(): LiveData<AppState> {
        return state
    }


    /// Зачем private передавать в обычную функуцию

    fun getDataFromRemote() {
        state.value = AppState.Loading
        // Дальше загрузка из ретрофита
        Thread {
            sleep(1000)
            state.postValue(AppState.Success(getNowPlayingList(), getUpComingList()))
        }.start()
    }




}