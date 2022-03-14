package com.example.express24_task.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.express24_task.api.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel :
    ViewModel() {

    private val movieDetailRepository = MovieDetailRepository()
    val movieDetailLiveData = MutableLiveData<MovieDetailState>()
    val movieActorLiveData = MutableLiveData<MovieActorState>()




    fun getMovieDetail(movieId: Long) {
        movieDetailLiveData.postValue(MovieDetailState.Loading)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                movieDetailRepository.getMovieDetailRepository(movieId).execute()
            }
            if (result.isSuccessful) {
                movieDetailLiveData.postValue(MovieDetailState.Succes(result.body()!!))
            } else {
                movieDetailLiveData.postValue(MovieDetailState.Error)
            }
        }
    }

    fun getMovieActor(movieId: Long) {
        movieActorLiveData.postValue((MovieActorState.Loading))
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                movieDetailRepository.getMovieActorRepository(movieId).execute()
            }
            if (result.isSuccessful) {
                movieActorLiveData.postValue(MovieActorState.Succes(result.body()!!))
            } else {
                movieActorLiveData.postValue(MovieActorState.Error)
            }
        }
    }
}
