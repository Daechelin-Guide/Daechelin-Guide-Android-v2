package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.comment.LocalReview
import com.dgsw.guidedaechelin.presentation.utils.MealType

interface ReviewLocalDataSource {

    suspend fun insertReview(

        localReview : LocalReview

    )

    suspend fun isReviewed(

        date : String,
        mealType: MealType

    ) : Boolean
}