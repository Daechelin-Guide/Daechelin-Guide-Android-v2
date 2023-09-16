package com.dgsw.guidedaechelin.domain.repository.comment

import com.dgsw.guidedaechelin.domain.model.comment.LocalReview
import com.dgsw.guidedaechelin.presentation.utils.MealType

interface LocalReviewRepository {

    suspend fun insertReview(
        localReview: LocalReview
    )

    suspend fun isReviewed(
        date : String,
        mealType: MealType
    ) : Boolean
}