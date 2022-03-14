package com.example.express24_task.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.express24_task.api.repository.MovieRepository
import com.example.express24_task.data.responce.MovieResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieFragmentViewModel : ViewModel() {
    private val  movieRepository = MovieRepository()
    val movieLiveData = MutableLiveData<MovieState>()
    var popularResponce  : MovieResponse? = null
    var  topRatedResponce : MovieResponse? = null
    var upComingResponce : MovieResponse? = null


    fun getPopularMovies() {
        if(popularResponce!=null){
           movieLiveData.postValue(MovieState.Succes(popularResponce!!))
            return
        }
        movieLiveData.postValue(MovieState.Loading)
        viewModelScope.launch(context  = CoroutineExceptionHandler { coroutineContext, throwable ->
            println(throwable.message)
        }) {
            val result = withContext(Dispatchers.IO){
                movieRepository.getPopularMovie().execute()
            }

            if (result.isSuccessful){
                movieLiveData.postValue(MovieState.Succes(result.body()!!))
                popularResponce = result.body()

            } else{
                movieLiveData.postValue(MovieState.Error)
            }
        }
    }

    fun getTopRatedMovies(){
        if(topRatedResponce!=null){
            movieLiveData.postValue(MovieState.Succes(topRatedResponce!!))
            return
        }

        movieLiveData.postValue(MovieState.Loading)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                movieRepository.getTopRatedMovie().execute()

            }
            if (result.isSuccessful){
                movieLiveData.postValue(MovieState.Succes(result.body()!!))
                topRatedResponce = result.body()
            }else{
                movieLiveData.postValue(MovieState.Error)
            }
        }
    }

    fun getUpComingMovies(){
        if(upComingResponce!=null){
            movieLiveData.postValue(MovieState.Succes(upComingResponce!!))
            return
        }

        movieLiveData.postValue(MovieState.Loading)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                movieRepository.getUpComingMovie().execute()
            }
            if (result.isSuccessful){
                movieLiveData.postValue(MovieState.Succes(result.body()!!))
                upComingResponce = result.body()
            }else{
                movieLiveData.postValue(MovieState.Error)
            }
        }
    }
}