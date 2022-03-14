package com.example.express24_task.api.repository

import com.example.express24_task.api.NetworkManager

import com.example.express24_task.data.responce.MovieResponse
import retrofit2.Call

class MovieRepository {

    fun getPopularMovie() : Call<MovieResponse> {
        return NetworkManager.getApiService()
            .getPopular("e99046b6df707a05a74e9c040eaf6400")
    }
    fun getTopRatedMovie() : Call<MovieResponse> {
        return NetworkManager.getApiService()
            .getTopRated("e99046b6df707a05a74e9c040eaf6400")
    }
    fun getUpComingMovie() : Call<MovieResponse> {
        return NetworkManager.getApiService()
            .getUpComing("e99046b6df707a05a74e9c040eaf6400")
    }
}