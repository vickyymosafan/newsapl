package com.example.newsapl.data.api

import com.example.newsapl.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "7e9f83ca5e1e4b28bc6d7ea27b663b6c",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): NewsResponse

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String = "technology",
        @Query("apiKey") apiKey: String = "7e9f83ca5e1e4b28bc6d7ea27b663b6c",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "publishedAt"
    ): NewsResponse
}
