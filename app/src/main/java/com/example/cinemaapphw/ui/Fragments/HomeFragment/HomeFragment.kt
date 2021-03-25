package com.example.cinemaapphw.ui.Fragments.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapphw.R
import com.example.cinemaapphw.databinding.FragmentHomeBinding
import com.example.testcinema.ui.ViewModelHome.AppState
import com.example.testcinema.ui.ViewModelHome.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        homeViewModel.getDataFromRemote()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                //Получение данных
                binding.loadingLayout.visibility = View.GONE
                //Передача списков в адаптеры
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                        .make(
                                binding.homeFragmentTextViewTitleFirst,
                                getString(R.string.error),
                                Snackbar.LENGTH_INDEFINITE
                        )
                        .setAction(getString(R.string.reload)) { homeViewModel.getDataFromRemote() }
                        .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}