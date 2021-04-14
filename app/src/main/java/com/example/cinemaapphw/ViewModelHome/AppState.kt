package com.example.testcinema.ui.ViewModelHome

import com.example.testcinema.DataClasses.Cinema

sealed class AppState {
    //data class Success(val CinemaNowPlayingList: List<Cinema>, val CinemaUpcomingList: List<Cinema>) : AppState()
    data class Success(val CinemaNowPlayingList: List<Cinema>) : AppState()
    data class SuccessDetails(val cinema: Cinema): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}