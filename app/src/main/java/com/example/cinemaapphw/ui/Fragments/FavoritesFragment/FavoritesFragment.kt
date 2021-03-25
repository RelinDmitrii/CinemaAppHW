package com.example.cinemaapphw.ui.Fragments.FavoritesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapphw.R

class FavoritesFragment : Fragment() {

////    private lateinit var notificationsViewModel: HomeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        notificationsViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        val root = inflater.inflate(R.layout.fragment_ratings, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//
//        notificationsViewModel.getData().observe(viewLifecycleOwner, Observer {
//            textView.text = it.toString()
//        })
//        return root
//    }
}