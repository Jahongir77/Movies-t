package com.example.express24_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.express24_task.R
import com.example.express24_task.data.responce.MovieActorResponce
import com.example.express24_task.databinding.ItemMovieActorBinding
import com.example.express24_task.ui.actordetail.ActorDetailFragment


class MovieActorAdapter(private val data : List<MovieActorResponce>) : RecyclerView.Adapter<MovieActorAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemMovieActorBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ItemHolder(private  val binding: ItemMovieActorBinding):RecyclerView.ViewHolder(binding.root){

        private val card = itemView.findViewById<CardView>(R.id.card)

        init {
            card.setOnClickListener{
                val bundle = bundleOf(
                    ActorDetailFragment.DETAIL_ID to data [adapterPosition].id
                )
                binding.root.findNavController()
                    .navigate(R.id.action_movieDetailFragment_to_actorDetailFragment, bundle)
            }
        }

        fun bind (item : MovieActorResponce){
            binding.actorsName.text = item.name
            binding.condition.text = item.character
            binding.numberEpisodes.text = item.popularity.toString()

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + item.profilePath)
                .into(binding.actorImage)

        }
    }
}