package com.dgsw.guidedaechelin.presentation.di.module

import com.dgsw.guidedaechelin.data.datasource.*
import com.dgsw.guidedaechelin.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun providesMenuRemoteDataSource(
        menuRemoteDataSourceImpl: MenuRemoteDataSourceImpl
    ): MenuRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesRatingRemoteDataSource(
        ratingRemoteDataSourceImpl: RatingRemoteDataSourceImpl
    ): RatingRemoteDataSource

    @Singleton
    @Binds
    abstract fun providesRankingRemoteDataSource(
        rankingRemoteDataSourceImpl: RankingRemoteDataSourceImpl
    ): RankingRemoteDataSource


}
