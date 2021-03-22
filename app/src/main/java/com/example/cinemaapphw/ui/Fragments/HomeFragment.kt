package com.example.cinemaapphw.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapphw.R
import com.example.cinemaapphw.databinding.FragmentHomeBinding
import com.example.testcinema.ui.main.MyViewModel

class HomeFragment : Fragment() {

    private lateinit var myViewModel: MyViewModel
    private lateinit var ui: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myViewModel =
            ViewModelProvider(this).get(MyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        ui = FragmentHomeBinding.bind(root)

        ui.homeFragmentTextViewTitleFirst.setOnClickListener{
            myViewModel.setText()
        }

        myViewModel.getData().observe(viewLifecycleOwner, Observer {
            ui.homeFragmentTextViewTitleFirst.text = it.toString()
        })
        return root
    }
}