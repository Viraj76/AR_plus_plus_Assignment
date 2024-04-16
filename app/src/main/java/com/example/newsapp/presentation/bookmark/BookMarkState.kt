package com.example.newsapp.presentation.bookmark

import com.example.newsapp.domain.model.Article

data class BookMarkState(
    val articles: List<Article> = emptyList()
)