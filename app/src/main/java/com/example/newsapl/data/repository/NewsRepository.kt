package com.example.newsapl.data.repository

import com.example.newsapl.data.api.RetrofitClient
import com.example.newsapl.data.model.Article
import com.example.newsapl.data.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepository {
    private val apiService = RetrofitClient.newsApiService

    fun getTopHeadlines(page: Int = 1): Flow<Result<List<Article>>> = flow {
        try {
            val response = apiService.getTopHeadlines(page = page)
            emit(Result.success(response.articles))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getEverything(query: String = "technology", page: Int = 1): Flow<Result<List<Article>>> = flow {
        try {
            val response = apiService.getEverything(query = query, page = page)
            emit(Result.success(response.articles))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
