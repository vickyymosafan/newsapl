package com.example.newsapl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapl.data.model.Article
import com.example.newsapl.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()
    
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()
    
    init {
        loadTopHeadlines()
        loadAllNews()
    }
    
    fun loadTopHeadlines() {
        viewModelScope.launch {
            _uiState.update { it.copy(isHeadlinesLoading = true) }
            repository.getTopHeadlines().collect { result ->
                result.fold(
                    onSuccess = { articles ->
                        _uiState.update { 
                            it.copy(
                                headlines = articles,
                                isHeadlinesLoading = false,
                                headlinesError = null
                            )
                        }
                    },
                    onFailure = { error ->
                        _uiState.update { 
                            it.copy(
                                isHeadlinesLoading = false,
                                headlinesError = error.message
                            )
                        }
                    }
                )
            }
        }
    }
    
    fun loadAllNews(refresh: Boolean = false) {
        viewModelScope.launch {
            if (refresh) {
                _uiState.update { 
                    it.copy(
                        isAllNewsLoading = true,
                        allNewsPage = 1,
                        allNews = emptyList()
                    )
                }
            } else {
                _uiState.update { it.copy(isAllNewsLoading = true) }
            }
            
            repository.getEverything(page = _uiState.value.allNewsPage).collect { result ->
                result.fold(
                    onSuccess = { articles ->
                        _uiState.update { currentState ->
                            val updatedNews = if (refresh) articles else currentState.allNews + articles
                            currentState.copy(
                                allNews = updatedNews,
                                isAllNewsLoading = false,
                                allNewsError = null,
                                allNewsPage = currentState.allNewsPage + 1,
                                canLoadMore = articles.isNotEmpty()
                            )
                        }
                    },
                    onFailure = { error ->
                        _uiState.update { 
                            it.copy(
                                isAllNewsLoading = false,
                                allNewsError = error.message
                            )
                        }
                    }
                )
            }
        }
    }
    
    fun refreshAllData() {
        loadTopHeadlines()
        loadAllNews(refresh = true)
    }
}

data class NewsUiState(
    val headlines: List<Article> = emptyList(),
    val allNews: List<Article> = emptyList(),
    val isHeadlinesLoading: Boolean = false,
    val isAllNewsLoading: Boolean = false,
    val headlinesError: String? = null,
    val allNewsError: String? = null,
    val allNewsPage: Int = 1,
    val canLoadMore: Boolean = true
)
