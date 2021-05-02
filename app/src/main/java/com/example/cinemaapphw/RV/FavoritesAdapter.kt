package com.example.testcinema.RV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithkotlin.room.FavoriteEntity
import com.example.cinemaapphw.R
import com.example.cinemaapphw.databinding.FavoritesFragmentItemBinding



class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.RecyclerItemViewHolder>() {

    private var data: List<FavoriteEntity> = arrayListOf()

    fun setData(data: List<FavoriteEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesAdapter.RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorites_fragment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: FavoritesFragmentItemBinding = FavoritesFragmentItemBinding.bind(view)

        fun bind(data: FavoriteEntity) {
            if(layoutPosition != RecyclerView.NO_POSITION){
                binding.FavoriteFragmentRecyclerItemTextView.text = data.title
            }
        }
    }
}