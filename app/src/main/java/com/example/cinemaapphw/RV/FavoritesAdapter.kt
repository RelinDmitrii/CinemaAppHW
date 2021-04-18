//package com.example.testcinema.RV
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.androidwithkotlin.room.FavoriteEntity
//import com.example.testcinema.DataClasses.Cinema
//import com.example.testcinema.R
//import com.example.testcinema.databinding.FavoritesItemBinding
//
//
//
//class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.RecyclerItemViewHolder>() {
//
//    private var data: List<Cinema> = arrayListOf()
//
//    fun setData(data: List<FavoriteEntity>) {
//        this.data = data
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): FavoritesAdapter.RecyclerItemViewHolder {
//        return RecyclerItemViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.fragment_favorites, parent, false) as View
//        )
//    }
//
//    override fun onBindViewHolder(holder: FavoritesAdapter.RecyclerItemViewHolder, position: Int) {
//        holder.bind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        private val binding: FavoritesItemBinding = FavoritesItemBinding.bind(view)
//
//        fun bind(data: Cinema) {
//            if(layoutPosition != RecyclerView.NO_POSITION){
//                binding
//            }
//        }
//    }
//}