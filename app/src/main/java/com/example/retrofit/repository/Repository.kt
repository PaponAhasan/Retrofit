package com.example.retrofit.repository

import com.example.retrofit.api.RetrofitInstance
import com.example.retrofit.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }
    suspend fun getPostNumber(number: Int): Response<Post>{
        return RetrofitInstance.api.getPostNumber(number)
    }
    suspend fun getNumberPosts(userId: Int, sort: String, order: String): Response<List<Post>>{
        return RetrofitInstance.api.getNumberPosts(userId, sort, order)
    }

    suspend fun getNumberAllPost(userId: Int, options: Map<String, String>): Response<List<Post>>{
        return RetrofitInstance.api.getNumberAllPost(userId, options)
    }
}