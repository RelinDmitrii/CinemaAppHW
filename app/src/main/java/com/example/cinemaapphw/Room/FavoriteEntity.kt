package com.example.androidwithkotlin.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val TITLE = "title"

@Entity
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String = "",
)
