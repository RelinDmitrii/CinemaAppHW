package com.example.cinemaapphw.ui.Fragments.MapsFragment

import android.app.Application
import android.location.Geocoder
import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import java.io.IOException


class MapsVIewModel (@NonNull application: Application,
                     private val liveDataTextAddress: MutableLiveData<String> = MutableLiveData(),
                     val markers: MutableLiveData<ArrayList<Marker>> = MutableLiveData()
) : AndroidViewModel(application) {



     fun getAddressAsync(location: LatLng) {
        getApplication<Application>()?.let {
            val geoCoder = Geocoder(it)
            val handler = Handler(Looper.getMainLooper())
            Thread {
                try {
                    val addresses =
                        geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                    handler.post {
                        liveDataTextAddress.value = addresses[0].getAddressLine(0).toString()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    fun addMarker(marker: Marker) {
        markers.value?.add(marker)
    }

}
