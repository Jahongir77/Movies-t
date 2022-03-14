package com.example.express24_task.api

import com.example.express24_task.data.responce.ActorDetailResponce
import com.example.express24_task.data.responce.ActorResponce
import com.example.express24_task.data.responce.DetailResponce
import com.example.express24_task.data.responce.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apikey: String
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apikey: String
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") apikey: String
    ): Call<MovieResponse>

    @GET("movie/{movie}")
    fun getDetailMovie(
        @Path("movie") movieId: Long,
        @Query("api_key") apikey: String
    ): Call<DetailResponce>

    @GET("movie/{movie}/casts")
    fun getActorMovie(
        @Path("movie") movieId: Long,
        @Query("api_key") apikey: String
    ): Call<ActorResponce>

    @GET("person/1136406")
    fun getActorDetail(
        @Query("api_key") apikey: String
    ): Call<ActorDetailResponce>
}