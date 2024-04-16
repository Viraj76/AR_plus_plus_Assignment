package com.example.newsapp.di

import android.app.Application
import androidx.datastore.preferences.protobuf.Api
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.local.NewsTypeConvertor
import com.example.newsapp.data.manager.LocalUserManagerImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.newsapp.domain.usecases.appentry.ReadUserEntry
import com.example.newsapp.domain.usecases.appentry.SaveUserEntry
import com.example.newsapp.domain.usecases.news.DeleteNews
import com.example.newsapp.domain.usecases.news.GetArticle
import com.example.newsapp.domain.usecases.news.GetArticles
import com.example.newsapp.domain.usecases.news.GetNews
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.domain.usecases.news.SearchNews
import com.example.newsapp.domain.usecases.news.UpsertArticle
import com.example.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Signed
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        ReadUserEntry(localUserManager),
        SaveUserEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: NewsDao) : NewsRepository{
        return NewsRepositoryImpl(newsApi,newsDao )
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteNews = DeleteNews(newsRepository),
            getArticles = GetArticles(newsRepository),
            getArticle =  GetArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao




}