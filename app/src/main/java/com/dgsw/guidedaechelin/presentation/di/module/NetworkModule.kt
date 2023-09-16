package com.dgsw.guidedaechelin.presentation.di.module

import com.dgsw.guidedaechelin.presentation.di.FirstRemoteRetrofit
import com.dgsw.guidedaechelin.presentation.di.SecondRemoteRetrofit
import com.dgsw.guidedaechelin.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

        @FirstRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://34.64.210.84:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SecondRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://43.201.27.119:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealApi( @FirstRemoteRetrofit retrofit: Retrofit): MealService =
        retrofit.create(MealService::class.java)

    @Provides
    @Singleton
    fun provideCommentApi( @FirstRemoteRetrofit retrofit: Retrofit): CommentService =
        retrofit.create(CommentService::class.java)

    @Provides
    @Singleton
    fun provideRatingApi( @FirstRemoteRetrofit retrofit: Retrofit): RatingService =
        retrofit.create(RatingService::class.java)

    @Provides
    @Singleton
    fun provideNewReviewApi( @SecondRemoteRetrofit retrofit: Retrofit): NewReviewService =
        retrofit.create(NewReviewService::class.java)

    @Provides
    @Singleton
    fun provideNewMealApi( @SecondRemoteRetrofit retrofit: Retrofit): NewMealService =
        retrofit.create(NewMealService::class.java)

    @Provides
    @Singleton
    fun provideRankingApi( @SecondRemoteRetrofit retrofit: Retrofit): RankingService =
        retrofit.create(RankingService::class.java)


}