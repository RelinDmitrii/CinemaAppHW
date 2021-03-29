package com.example.cinemaapphw.ui.Fragments.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapphw.databinding.FragmentDetailBinding
import com.example.testcinema.DataClasses.Cinema

class DetailFragment : Fragment() {

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cinema = arguments?.getParcelable<Cinema>(BUNDLE_DETAIL)
        if (cinema != null) {
            //Заполняем по всем полям, доделаю как начнем подгружать данные
            binding.detailFragmentMtvOriginalNameOfCinema.text = cinema.originalTitle

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

}