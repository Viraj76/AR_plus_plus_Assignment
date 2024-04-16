package com.example.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteNews: DeleteNews,
    val getArticles: GetArticles,
    val getArticle: GetArticle
)