package com.example.cinemaapphw.RV

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapphw.databinding.NowPlayingCardBinding
import com.example.testcinema.DataClasses.Cinema

class NowPlayingViewHolder(
    itemView: View,
    private val onItemClickListener: CinemaListRvAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemView), CinemaListRvAdapter.Bendable {

    private val binding: NowPlayingCardBinding = NowPlayingCardBinding.bind(itemView)

    override fun bind(cinema: Cinema) {

        Glide
            .with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${cinema.posterPath}")
            .into(binding.ivNowPlayingCardPosterPath);

        binding.tvNowPlayingCardOriginalTitle.text = cinema.originalTitle
        binding.tvNowPlayingCardPopularity.text = cinema.popularity.toString()
        binding.tvNowPlayingCardReleaseDate.text = cinema.releaseDate
        itemView.setOnClickListener {
            onItemClickListener.onItemClick(cinema)
        }
    }
}
