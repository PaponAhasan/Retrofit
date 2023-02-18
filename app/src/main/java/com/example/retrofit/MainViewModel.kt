package com.example.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.Post
import com.example.retrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseNumber: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponsePosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponseAllPost: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun getPostNumber(number: Int){
        viewModelScope.launch {
            val response = repository.getPostNumber(number)
            myResponseNumber.value = response
        }
    }
    fun getNumberPosts(userId: Int, sort: String, order: String){
        viewModelScope.launch {
            val response = repository.getNumberPosts(userId, sort, order)
            myResponsePosts.value = response
        }
    }
    fun getNumberAllPost(userId: Int, options:Map<String, String>){
        viewModelScope.launch {
            val response = repository.getNumberAllPost(userId, options)
            myResponseAllPost.value = response
        }
    }
}