package com.example.express24_task.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.express24_task.R
import com.example.express24_task.adapter.MovieAdapter
import com.example.express24_task.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayout

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel  : MovieFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
        viewModel.getPopularMovies()

        viewModel.movieLiveData.observe(viewLifecycleOwner){
            when (it) {
                is MovieState.Loading -> {
                    binding.progress.isVisible = true
                }
                is MovieState.Succes -> {
                    binding.progress.isVisible = false
                    binding.recycler.adapter = MovieAdapter(it.data.results)

                }
                is MovieState.Error -> {
                    Toast.makeText(context, "error" , Toast.LENGTH_SHORT).show()
                }
            }
        }

       binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
           override fun onTabSelected(tab: TabLayout.Tab) {

               when (tab.position) {
                   0 -> viewModel.getPopularMovies()
                   1 -> viewModel.getTopRatedMovies()
                   2 -> viewModel.getUpComingMovies()
               }

           }
           override fun onTabUnselected(tab: TabLayout.Tab?) {}
           override fun onTabReselected(tab: TabLayout.Tab?) {}

       })
    }

}