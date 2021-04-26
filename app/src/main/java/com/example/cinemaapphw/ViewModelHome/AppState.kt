package com.example.testcinema.ui.ViewModelHome

import com.example.androidwithkotlin.room.FavoriteEntity
import com.example.testcinema.DataClasses.Cinema

sealed class AppState {
    //data class Success(val CinemaNowPlayingList: List<Cinema>, val CinemaUpcomingList: List<Cinema>) : AppState()
    data class SuccessNowPlaying(var CinemaNowPlayingList: List<Cinema>) : AppState()
    data class SuccessUpcoming(var CinemaUpcomingList: List<Cinema>) : AppState()
    data class SuccessFavorites(val FavoritesList: List<FavoriteEntity>) : AppState()
    data class SuccessDetails(val cinema: Cinema): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}