package com.example.testcinema.DataClasses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cinema(
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("original_title")
        @Expose
        val originalTitle: String,
        @SerializedName("overview")
        @Expose
        val overview: String,
        @SerializedName("release_date")
        @Expose
        val releaseDate: String,
        @SerializedName("popularity")
        @Expose
        val popularity: Double,
        @SerializedName("poster_path")
        @Expose
        val posterPath: String,
        @SerializedName("status")
        @Expose
        val status: String
) : Parcelable

// ЗАГЛУШКИ
fun getNowPlayingList(): List<Cinema> {
        return listOf(
                Cinema(13,"Кино1", "Кино1", "дата1", 6.5, "Кино1","Released"),
                Cinema(14,"Кино2", "Кино2", "дата2", 6.5, "Кино2","Released"),
                Cinema(15,"Кино3", "Кино3", "дата3", 6.5, "Кино3","Released"),
                Cinema(16,"Кино4", "Кино4", "дата4", 6.5, "Кино4","Released"),
        )
}

fun getUpComingList(): List<Cinema> {
        return listOf(
                Cinema(7,"Кино5", "Кино5", "дата5", 6.5, "Кино5","Post Production"),
                Cinema(8,"Кино6", "Кино6", "дата6", 6.5, "Кино6","Post Production"),
                Cinema(9,"Кино7", "Кино7", "дата7", 6.5, "Кино7","Post Production"),
                Cinema(10,"Кино8", "Кино8", "дата8", 6.5, "Кино8","Post Production"),
        )
}

