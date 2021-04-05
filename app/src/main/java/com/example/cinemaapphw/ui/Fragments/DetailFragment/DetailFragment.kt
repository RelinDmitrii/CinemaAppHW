package com.example.cinemaapphw.ui.Fragments.DetailFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.cinemaapphw.Support.CinemaLoader
import com.example.cinemaapphw.databinding.FragmentDetailBinding
import com.example.testcinema.DataClasses.Cinema
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), CinemaLoader.CinemaLoaderListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Cinema>(BUNDLE_DETAIL)?.let {
            it.also {
                val loader = CinemaLoader(this, it.id)
                loader.loadCinema()
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_DETAIL = "cinema"

        fun newInstance(bundle: Bundle): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onLoaded(cinema: Cinema) {
        displayCinema(cinema)
    }

    override fun onFailed(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    private fun displayCinema(cinema: Cinema) {
        with(binding) {
            mtv_title.text = cinema.originalTitle
            mtv_original_name_of_cinema.text = cinema.popularity.toString()
        }

    }
}