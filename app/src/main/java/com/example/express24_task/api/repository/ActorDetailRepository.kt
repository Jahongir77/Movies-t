package com.example.express24_task.api.repository

import com.example.express24_task.api.NetworkManager
import com.example.express24_task.data.responce.ActorDetailResponce
import com.example.express24_task.data.responce.DetailResponce
import retrofit2.Call

class ActorDetailRepository {

    fun getMovieActorDetail() : Call<ActorDetailResponce> {
        return NetworkManager.getApiService()
            .getActorDetail("e99046b6df707a05a74e9c040eaf6400")
    }
}