package com.example.cinemaapphw.RV

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapphw.databinding.UpcomingCardBinding
import com.example.testcinema.DataClasses.Cinema

class UpComingViewHolder(itemView: View, private val onItemClickListener: CinemaListRvAdapter.OnItemClickListener): RecyclerView.ViewHolder(itemView), CinemaListRvAdapter.Bendable {

    private val binding: UpcomingCardBinding = UpcomingCardBinding.bind(itemView)

    override fun bind(cinema: Cinema) {
        binding.tvUpcomingCardOriginalTitle.text = cinema.originalTitle
        binding.tvUpcomingCardReleaseDate.text = cinema.releaseDate
        itemView.setOnClickListener {
            onItemClickListener.onItemClick(cinema)
        }
    }
}