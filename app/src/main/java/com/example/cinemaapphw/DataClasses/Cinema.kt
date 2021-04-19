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
    @SerializedName("title")
    @Expose
    val title: String,
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
    val status: String,
    @SerializedName("runtime")
    @Expose
    val runtime: Int,
    @SerializedName("adult")
    @Expose
    val adult: Boolean
) : Parcelable



