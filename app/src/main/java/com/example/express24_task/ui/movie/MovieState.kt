package com.example.express24_task.ui.movie


import com.example.express24_task.data.responce.MovieResponse
sealed interface  MovieState {
    object Loading : MovieState
    data class Succes(val data: MovieResponse) : MovieState
    object Error : MovieState
}