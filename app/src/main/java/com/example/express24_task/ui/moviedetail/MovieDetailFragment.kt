package com.example.express24_task.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.express24_task.R
import com.example.express24_task.adapter.MovieActorAdapter
import com.example.express24_task.databinding.WindowMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.window_movie_detail) {
    private var _binding: WindowMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()
    private var movieId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getLong(ITEM_ID)?:-1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WindowMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actorRecycler.layoutManager = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.HORIZONTAL, false
        )


        viewModel.getMovieDetail(movieId)

        viewModel.movieDetailLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is MovieDetailState.Loading -> {
                    binding.detailProgres.isVisible = true
                }
                is MovieDetailState.Succes -> {
                    binding.detailProgres.isVisible = false

                    binding.tvTitel.text = it.data.title
                    binding.movieBudjetPrice.text = it.data.budget.toString()
                    binding.moviDurationTime.text = it.data.runtime.toString()
                    binding.language.text = it.data.originalLanguage
                    binding.voteCount.text = it.data.voteCount.toString()
                    binding.owerview.text = it.data.overview

                    Glide.with(requireActivity())
                        .load("https://image.tmdb.org/t/p/w500" + it.data.backdropPath)
                        .into(binding.detailImage)
                }
                is MovieDetailState.Error -> {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.getMovieActor(movieId)

        viewModel.movieActorLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is MovieActorState.Loading -> {

                }
                is MovieActorState.Succes -> {
                    binding.actorRecycler.adapter = MovieActorAdapter(it.data.cast)
                }
                is MovieActorState.Error -> {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val ITEM_ID = "id"
    }


}
