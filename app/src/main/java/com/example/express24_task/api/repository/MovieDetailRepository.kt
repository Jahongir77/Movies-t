package com.example.express24_task.api.repository

import com.example.express24_task.api.NetworkManager
import com.example.express24_task.data.responce.ActorResponce
import com.example.express24_task.data.responce.DetailResponce

import retrofit2.Call

class MovieDetailRepository {

    fun getMovieDetailRepository(movieId: Long): Call<DetailResponce> {
        return NetworkManager.getApiService()
            .getDetailMovie(movieId,"e99046b6df707a05a74e9c040eaf6400" )
    }

    fun getMovieActorRepository(movieId: Long): Call<ActorResponce> {
        return NetworkManager.getApiService()
            .getActorMovie(movieId,"e99046b6df707a05a74e9c040eaf6400" )
    }
}