package com.example.express24_task.ui.actordetail

import com.example.express24_task.data.responce.ActorDetailResponce
import com.example.express24_task.data.responce.ActorResponce

sealed interface   ActorDetailState {
    object Loading : ActorDetailState
    data class Succes(val data: ActorDetailResponce) : ActorDetailState
    object Error : ActorDetailState

}