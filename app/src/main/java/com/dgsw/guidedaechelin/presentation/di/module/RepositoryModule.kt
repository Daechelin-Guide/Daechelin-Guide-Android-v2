package com.dgsw.guidedaechelin.presentation.di.module

import com.dgsw.guidedaechelin.data.repository.MenuRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.RankingRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.RatingRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.ReportRepositoryImpl
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.domain.repository.RankingRepository
import com.dgsw.guidedaechelin.domain.repository.RatingRepository
import com.dgsw.guidedaechelin.domain.repository.ReportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMenuRepository(

        menuRepositoryImpl: MenuRepositoryImpl

    ): MenuRepository {
        return menuRepositoryImpl
    }
    @Provides
    @Singleton
    fun provideRatingRepository(

        ratingRepositoryImpl: RatingRepositoryImpl

    ): RatingRepository {
        return ratingRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideRankingRepository(

        rankingRepositoryImpl: RankingRepositoryImpl

    ): RankingRepository {
        return rankingRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideReportRepository(

        reportRepositoryImpl: ReportRepositoryImpl

    ): ReportRepository {
        return reportRepositoryImpl
    }
}