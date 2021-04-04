package com.example.cinemaapphw.RV

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapphw.R
import com.example.testcinema.DataClasses.Cinema

class CinemaListRvAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cinema = listOf<Cinema>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface Bendable {
        fun bind(cinema: Cinema)
    }

    interface OnItemClickListener {
        fun onItemClick(cinema: Cinema)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0){
            NowPlayingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.now_playing_card, parent, false), onItemClickListener)
        } else{
            UpComingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upcoming_card, parent, false), onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Bendable).bind(cinema[position])
    }

    override fun getItemCount(): Int {
        return cinema.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (cinema[position].status == "Released") {
            0
        } else {
            1
        }
    }

}