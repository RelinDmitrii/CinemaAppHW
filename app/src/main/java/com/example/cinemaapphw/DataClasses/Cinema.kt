package com.example.testcinema.DataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
        @SerializedName("popularity")
        @Expose
        val popularity: Double,
        @SerializedName("poster_path")
        @Expose
        val posterPath: String,
        @SerializedName("status")
        @Expose
        val status: String
)
