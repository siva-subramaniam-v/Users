package com.example.users.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gorest.co.in/public/v2/"

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("users")
    suspend fun getUsersAsync(): List<NetworkUsers>

    @GET("posts")
    suspend fun getPostsAsync(): List<NetworkPosts>
}

object Network {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}