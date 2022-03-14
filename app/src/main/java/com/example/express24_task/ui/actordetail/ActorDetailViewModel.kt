package com.example.express24_task.ui.actordetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.express24_task.api.repository.ActorDetailRepository
import com.example.express24_task.ui.movie.MovieState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorDetailViewModel : ViewModel() {

    private val actorDetailRepository = ActorDetailRepository()
    val actorDetailLiveData = MutableLiveData<ActorDetailState>()

    fun getActorDetailMovies() {
        actorDetailLiveData.postValue(ActorDetailState.Loading)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                actorDetailRepository.getMovieActorDetail().execute()
            }

            if (result.isSuccessful){
                actorDetailLiveData.postValue(ActorDetailState.Succes(result.body()!!))
            } else{
                actorDetailLiveData.postValue(ActorDetailState.Error)
            }
        }
    }
}