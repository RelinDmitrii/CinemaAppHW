package com.example.cinemaapphw.ui.Fragments.DetailFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cinemaapphw.databinding.FragmentDetailBinding
import com.example.testcinema.DataClasses.Cinema
import com.example.testcinema.ui.ViewModelHome.AppState
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.function.Consumer

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }

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

        //arguments?.getParcelable<Cinema>(BUNDLE_DETAIL)?.let {
        arguments?.getInt(BUNDLE_DETAIL, 0)?.let {
            it.also {
//                val loader = CinemaLoader(this, it.id)
//                loader.loadCinema()
                viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
                viewModel.getCinemaFromRemoteSource(it)
            }
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessDetails -> {
                displayCinema(appState.cinema)
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

    private fun displayCinema(cinema: Cinema) {
        with(binding) {
            mtv_title.text = cinema.title
            mtv_original_name_of_cinema.text = cinema.originalTitle
            val genres = cinema.genres
            var finalGenres = ""
            genres.forEach { element ->
                    finalGenres += element.asJsonObject.get("name").asString + " "
            }

            mtv_genre.text = finalGenres
            mtv_run_time.text = cinema.runtime.toString()
            mtv_budget.text = cinema.budget.toString()
            mtv_revenue.text = cinema.revenue.toString()
            mtv_release_data.text = cinema.releaseDate
            mtv_overview.text = cinema.overview
            context?.let {
                Glide
                .with(it)
                .load("https://image.tmdb.org/t/p/w500/${cinema.posterPath}")
                .into(ivPoster); }

            iv_favorite.setOnClickListener(View.OnClickListener {
                viewModel.addToFavorites()
            })

        }
    }
}