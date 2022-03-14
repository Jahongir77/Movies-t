package com.example.express24_task.ui.moviedetail

import com.example.express24_task.data.responce.ActorResponce
import com.example.express24_task.data.responce.DetailResponce

sealed interface  MovieDetailState {
    object Loading : MovieDetailState
    data class Succes(val data: DetailResponce) : MovieDetailState
    object Error : MovieDetailState
}
sealed interface  MovieActorState {
    object Loading : MovieActorState
    data class Succes(val data: ActorResponce) : MovieActorState
    object Error : MovieActorState
}