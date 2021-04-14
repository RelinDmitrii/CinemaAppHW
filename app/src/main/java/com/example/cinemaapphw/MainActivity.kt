package com.example.cinemaapphw

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cinemaapphw.Support.MainBroadcastReceiver
import com.example.cinemaapphw.Support.Support
import com.example.cinemaapphw.ui.Fragments.FavoritesFragment.FavoritesFragment
import com.example.cinemaapphw.ui.Fragments.HomeFragment.HomeFragment
import com.example.cinemaapphw.ui.Fragments.RatingsFragment.RatingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver()
    lateinit var support: Support

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        support = Support(supportFragmentManager)

        if (savedInstanceState == null) {
            support.addFragment(HomeFragment(), false)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    support.addFragment(HomeFragment(), false)
                }
                R.id.navigation_ratings -> {
                    support.addFragment(RatingsFragment(), false)
                }
                R.id.navigation_favorites -> {
                    support.addFragment(FavoritesFragment(), false)
                }
            }
            true
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

}