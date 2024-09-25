package com.dgsw.guidedaechelin.presentation.di.module

import android.media.Rating
import android.view.Menu
import com.dgsw.guidedaechelin.presentation.di.FirstRemoteRetrofit
import com.dgsw.guidedaechelin.presentation.di.SecondRemoteRetrofit
import com.dgsw.guidedaechelin.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @FirstRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @SecondRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://54.180.155.53:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }
    @Provides
    @Singleton
    fun provideMenuApi( @SecondRemoteRetrofit retrofit: Retrofit): MenuService =
        retrofit.create(MenuService::class.java)

    @Provides
    @Singleton
    fun provideRatingApi( @SecondRemoteRetrofit retrofit: Retrofit): RatingService =
        retrofit.create(RatingService::class.java)

    @Provides
    @Singleton
    fun provideRankingApi( @SecondRemoteRetrofit retrofit: Retrofit): RankingService =
        retrofit.create(RankingService::class.java)

    @Provides
    @Singleton
    fun provideReportApi( @FirstRemoteRetrofit retrofit: Retrofit): ReportService =
        retrofit.create(ReportService::class.java)


}