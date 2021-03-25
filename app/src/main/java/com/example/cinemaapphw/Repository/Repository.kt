package com.example.testcinema.Repository

import com.example.testcinema.DataClasses.Cinema

interface Repository {

    fun getCinemaFromServer(): Cinema
    fun getCinemaFromLocalStorage(): Cinema


}