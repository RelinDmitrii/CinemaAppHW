package com.example.cinemaapphw.ui.Fragments.HomeFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinemaapphw.MainActivity
import com.example.cinemaapphw.R
import com.example.cinemaapphw.RV.CinemaListRvAdapter
import com.example.cinemaapphw.RV.NOW_PLAYING_TYPE
import com.example.cinemaapphw.RV.UPCOMING_TYPE
import com.example.cinemaapphw.databinding.FragmentHomeBinding
import com.example.cinemaapphw.ui.Fragments.DetailFragment.DetailFragment
import com.example.testcinema.DataClasses.Cinema
import com.example.testcinema.ui.ViewModelHome.AppState
import com.example.testcinema.ui.ViewModelHome.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private lateinit var cinemaListRvAdapter: CinemaListRvAdapter
    private lateinit var cinemaList2RvAdapter: CinemaListRvAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private val sharedPref by lazy { activity?.getSharedPreferences("Options" , Context.MODE_PRIVATE) }


    private val adapterListener = object : CinemaListRvAdapter.OnItemClickListener {
        override fun onItemClick(cinema: Cinema) {
            val bundle = Bundle()
            bundle.putInt(DetailFragment.BUNDLE_DETAIL, cinema.id)
            (activity as MainActivity).support.addFragment(DetailFragment.newInstance(bundle), true)
            }
        }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createAdapter()
        homeViewModel.getDataNowPlaying().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        homeViewModel.getDataUpcoming().observe(viewLifecycleOwner, {
            renderData(it)
        })
        homeViewModel.getDataFromRemote()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessNowPlaying -> {
                binding.loadingLayout.visibility = View.GONE
                if(sharedPref?.getBoolean("keyAdult", false) == true) {
                        appState.CinemaNowPlayingList = appState.CinemaNowPlayingList.filter { !it.adult }
                    }
                cinemaListRvAdapter.cinema = appState.CinemaNowPlayingList

            }
            is AppState.SuccessUpcoming -> {
                binding.loadingLayout.visibility = View.GONE
                if(sharedPref?.getBoolean("keyAdult", false) == true) {
                    appState.CinemaUpcomingList = appState.CinemaUpcomingList.filter { !it.adult }
                }
                cinemaList2RvAdapter.cinema = appState.CinemaUpcomingList
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE

            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.loadingLayout.showSnackBar(R.string.error)
                Snackbar
                        .make(
                                binding.homeFragmentTvTitleNowPlaying,
                                getString(R.string.error),
                                Snackbar.LENGTH_INDEFINITE
                        )
                        .setAction(getString(R.string.reload)) { homeViewModel.getDataFromRemote() }
                        .show()
            }
        }
    }

    private fun createAdapter() {
        cinemaListRvAdapter = CinemaListRvAdapter(adapterListener, NOW_PLAYING_TYPE)
        cinemaList2RvAdapter = CinemaListRvAdapter(adapterListener, UPCOMING_TYPE)
        binding.homeFragmentRecyclerFirst.adapter = cinemaListRvAdapter
        binding.homeFragmentRecyclerSecond.adapter = cinemaList2RvAdapter
    }

    private fun View.showSnackBar(
        @StringRes
        stringRes: Int,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(this, stringRes, length).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}