package com.dgsw.guidedaechelin.presentation.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

//    @Singleton
//    @Binds
//    abstract fun providesReviewLocalDataSource(
//        reviewLocalDataSourceImpl: ReviewLocalDataSourceImpl
//    ): ReviewLocalDataSource

}
