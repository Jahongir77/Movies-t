package com.example.express24_task.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.express24_task.R
import com.example.express24_task.data.responce.Responce
import com.example.express24_task.databinding.ItemFragmentMovieBinding
import com.example.express24_task.ui.moviedetail.MovieDetailFragment.Companion.ITEM_ID



class MovieAdapter(private val data: List<Responce>):RecyclerView.Adapter<MovieAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemFragmentMovieBinding.inflate(LayoutInflater.from(parent.context), parent ,false )
        return ItemHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    inner class ItemHolder(private val binding : ItemFragmentMovieBinding) : RecyclerView.ViewHolder(binding.root){
        private val liner = itemView.findViewById<LinearLayout>(R.id.liner)
      //  val imageView = itemView.findViewById<ImageView>(R.id.image)

        init {
            //imageView.clipToOutline = true
            liner.setOnClickListener{
                val bundle = bundleOf(
                ITEM_ID to data [adapterPosition].id
                )
                binding.root.findNavController()
                    .navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
            }
        }
        fun bind (item : Responce){
            binding.tvTitel.text = item.title

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + item.posterPath)
                .into(binding.image)

        }

    }

}