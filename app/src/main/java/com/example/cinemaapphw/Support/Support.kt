package com.example.cinemaapphw.Support

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cinemaapphw.R


class Support(private val fragmentManager: FragmentManager) {

    fun addFragment(fragment: Fragment, useBackStack: Boolean) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, fragment)
        if (useBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}