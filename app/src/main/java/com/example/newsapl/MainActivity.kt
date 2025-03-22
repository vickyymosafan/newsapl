package com.example.newsapl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapl.data.model.Article
import com.example.newsapl.ui.screens.ArticleDetailScreen
import com.example.newsapl.ui.screens.NewsScreen
import com.example.newsapl.ui.theme.NewsaplTheme
import com.example.newsapl.ui.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsaplTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsApp()
                }
            }
        }
    }
}

@Composable
fun NewsApp() {
    val viewModel: NewsViewModel = viewModel()
    var selectedArticle by remember { mutableStateOf<Article?>(null) }
    
    if (selectedArticle == null) {
        NewsScreen(
            viewModel = viewModel,
            onArticleClick = { article ->
                selectedArticle = article
            }
        )
    } else {
        ArticleDetailScreen(
            article = selectedArticle!!,
            onBackPressed = {
                selectedArticle = null
            }
        )
    }
}