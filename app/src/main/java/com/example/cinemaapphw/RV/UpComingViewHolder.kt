package com.example.cinemaapphw.RV

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapphw.databinding.UpcomingCardBinding
import com.example.testcinema.DataClasses.Cinema

class UpComingViewHolder(itemView: View, private val onItemClickListener: CinemaListRvAdapter.OnItemClickListener): RecyclerView.ViewHolder(itemView), CinemaListRvAdapter.Bendable {

    private val binding: UpcomingCardBinding = UpcomingCardBinding.bind(itemView)

    override fun bind(cinema: Cinema) {
        Glide
            .with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${cinema.posterPath}")
            .into(binding.ivUpcomingCardPosterPath);

        binding.tvUpcomingCardOriginalTitle.text = cinema.title
        binding.tvUpcomingCardReleaseDate.text = cinema.releaseDate
        itemView.setOnClickListener {
            onItemClickListener.onItemClick(cinema)
        }
    }
}