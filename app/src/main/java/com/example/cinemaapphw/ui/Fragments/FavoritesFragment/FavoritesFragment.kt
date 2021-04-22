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
import com.example.cinemaapphw.databinding.FragmentFavoritesBinding
import com.example.testcinema.RV.FavoritesAdapter
import com.example.testcinema.ui.ViewModelHome.AppState
import com.example.testcinema.ui.ViewModelHome.HomeViewModel

class FavoritesFragment : Fragment() {


    private lateinit var favoritesViewModel: FavoritesViewModel

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this).get(
            FavoritesViewModel::class.java
        )
    }
    private val adapter: FavoritesAdapter by lazy { FavoritesAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavoritesFragment.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllFavorites()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessFavorites -> {
                binding.rvFavoritesFragment.visibility = View.VISIBLE
                adapter.setData(appState.FavoritesList)
            }
//            is AppState.Loading -> {
//                binding.historyFragmentRecyclerview.visibility = View.GONE
//                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
//            }
//            is AppState.Error -> {
//                binding.historyFragmentRecyclerview.visibility = View.VISIBLE
//                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
//                binding.historyFragmentRecyclerview.showSnackBar(
//                    getString(R.string.error),
//                    getString(R.string.reload),
//                    {
//                        viewModel.getAllHistory()
//                    })
//            }
        }
    }
}