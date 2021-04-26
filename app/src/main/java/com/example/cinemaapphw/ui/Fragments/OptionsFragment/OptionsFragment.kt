package com.example.cinemaapphw.ui.Fragments.OptionsFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapphw.databinding.FragmentHomeBinding
import com.example.cinemaapphw.databinding.FragmentOptionsBinding


class OptionsFragment: Fragment() {

    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedPref = activity?.getSharedPreferences("Options", Context.MODE_PRIVATE)
        if(sharedPref?.getBoolean("keyAdult", false) == true){
            binding.checkBox.isChecked = true
        }
        binding.checkBox.setOnClickListener {
            if(binding.checkBox.isChecked){
                val editor = sharedPref?.edit()
                editor?.putBoolean("keyAdult", true)
                editor?.apply()
            } else {
                val editor = sharedPref?.edit()
                editor?.putBoolean("keyAdult", false)
                editor?.apply()
            }
        }
    }

}