package com.dgsw.guidedaechelin.data.repository.comment

import com.dgsw.guidedaechelin.data.datasource.ReviewLocalDataSource
import com.dgsw.guidedaechelin.domain.model.comment.LocalReview
import com.dgsw.guidedaechelin.domain.repository.comment.LocalReviewRepository
import com.dgsw.guidedaechelin.presentation.utils.MealType
import javax.inject.Inject

class LocalReviewRepositoryImpl @Inject constructor(

    private val reviewLocalDataSource: ReviewLocalDataSource

) : LocalReviewRepository {

    override suspend fun insertReview(localReview: LocalReview){

        reviewLocalDataSource.insertReview(localReview)
    }

    override suspend fun isReviewed(

        date : String,
        mealType: MealType

    ) : Boolean = reviewLocalDataSource.isReviewed(date, mealType)


}