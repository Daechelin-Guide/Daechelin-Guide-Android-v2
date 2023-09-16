package com.dgsw.guidedaechelin.presentation.di.module

import com.dgsw.guidedaechelin.data.datasource.CommentRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.MealRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.RatingRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.ReviewLocalDataSource
import com.dgsw.guidedaechelin.local.datasource.ReviewLocalDataSourceImpl
import com.dgsw.guidedaechelin.remote.datasource.CommentRemoteDataSourceImpl
import com.dgsw.guidedaechelin.remote.datasource.MealRemoteDataSourceImpl
import com.dgsw.guidedaechelin.remote.datasource.RatingRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesReviewLocalDataSource(
        reviewLocalDataSourceImpl: ReviewLocalDataSourceImpl
    ): ReviewLocalDataSource

}
