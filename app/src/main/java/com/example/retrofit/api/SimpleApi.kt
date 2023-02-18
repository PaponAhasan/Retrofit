package com.example.retrofit.api

import com.example.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    /* Get dynamic Post*/
    @GET("posts/{postNumber}")
    suspend fun getPostNumber(
        @Path("postNumber") number: Int
    ): Response<Post>

    /* Get List Of Post*/
    @GET("posts")
    suspend fun getNumberPosts(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    /* List Of Post using QueryMap*/
    @GET("posts")
    suspend fun getNumberAllPost(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>
}