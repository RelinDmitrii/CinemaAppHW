package com.example.cinemaapphw.Support

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.testcinema.DataClasses.Cinema
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@RequiresApi(Build.VERSION_CODES.N)
const val API = "871d5e779762da463a18cadbac0572b3"

class CinemaLoader(private val listener: CinemaLoaderListener, private val cinemaId: Int) {


    @RequiresApi(Build.VERSION_CODES.N)
    fun loadCinema() {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/${cinemaId}?api_key=${API}")
            val handler = Handler(Looper.getMainLooper())
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val cinema: Cinema =
                        Gson().fromJson(getLines(bufferedReader), Cinema::class.java)
                    handler.post { listener.onLoaded(cinema) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface CinemaLoaderListener {
        fun onLoaded(cinema: Cinema)
        fun onFailed(throwable: Throwable)
    }
}