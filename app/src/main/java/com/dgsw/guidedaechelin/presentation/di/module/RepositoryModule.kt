package com.dgsw.guidedaechelin.presentation.di.module

import com.dgsw.guidedaechelin.data.repository.comment.CommentRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.comment.LocalReviewRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.meal.MealRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.meal.NewMealRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.meal.RatingRepositoryImpl
import com.dgsw.guidedaechelin.data.repository.ranking.RankingRespositoryImpl
import com.dgsw.guidedaechelin.data.repository.review.NewReviewRepositoryImpl
import com.dgsw.guidedaechelin.domain.repository.comment.CommentRepository
import com.dgsw.guidedaechelin.domain.repository.comment.LocalReviewRepository
import com.dgsw.guidedaechelin.domain.repository.meal.MealRepository
import com.dgsw.guidedaechelin.domain.repository.meal.NewMealRepository
import com.dgsw.guidedaechelin.domain.repository.meal.RatingRepository
import com.dgsw.guidedaechelin.domain.repository.ranking.RankingRepository
import com.dgsw.guidedaechelin.domain.repository.review.NewReviewRepository
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
    fun provideMealRepository(

        mealRepositoryImpl: MealRepositoryImpl

    ):MealRepository{
        return mealRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideCommentRepository(

        commentRepositoryImpl: CommentRepositoryImpl

    ): CommentRepository {
        return commentRepositoryImpl
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
    fun provideLocalReviewRepository(

        localReviewRepositoryImpl: LocalReviewRepositoryImpl

    ): LocalReviewRepository {
        return localReviewRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideNewMealRepository(

        newMealRepositoryImpl: NewMealRepositoryImpl

    ): NewMealRepository {
        return newMealRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideNewReviewRepository(

        newReviewRepositoryImpl: NewReviewRepositoryImpl

    ): NewReviewRepository {
        return newReviewRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideRankingRepository(

        rankingRepositoryImpl: RankingRespositoryImpl

    ): RankingRepository {
        return rankingRepositoryImpl
    }
}