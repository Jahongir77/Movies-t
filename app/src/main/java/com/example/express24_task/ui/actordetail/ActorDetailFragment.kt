package com.example.express24_task.ui.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.express24_task.adapter.MovieAdapter
import com.example.express24_task.databinding.WindowActorDetailBinding
import com.example.express24_task.ui.movie.MovieState
import com.example.express24_task.ui.moviedetail.MovieDetailFragment

class ActorDetailFragment : Fragment() {

    private var _binding: WindowActorDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel  : ActorDetailViewModel by viewModels()



    private val id by lazy { arguments?.getString(DETAIL_ID) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WindowActorDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getActorDetailMovies()

        viewModel.actorDetailLiveData.observe(viewLifecycleOwner){
            when (it) {
                is ActorDetailState.Loading -> {

                }
                is ActorDetailState.Succes -> {
                    binding.actorName.text = it.data.name
                    binding.actorCountry.text = it.data.placeOfBirth
                    binding.actorBirthday.text = it.data.birthday
                    binding.biography.text = it.data.biography

                    Glide.with(requireActivity())
                        .load("https://image.tmdb.org/t/p/w500" + it.data.profilePath)
                        .into(binding.actorImage)
                }
                is ActorDetailState.Error -> {
                    Toast.makeText(context, "error" , Toast.LENGTH_SHORT).show()
                }
            }
        }

}
    companion object {
        const val DETAIL_ID = "id"
    }
}