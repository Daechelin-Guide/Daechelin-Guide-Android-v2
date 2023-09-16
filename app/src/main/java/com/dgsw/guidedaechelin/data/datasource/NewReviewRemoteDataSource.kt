package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.meal.NewMeal
import com.dgsw.guidedaechelin.domain.model.review.*

interface NewReviewRemoteDataSource {

    suspend fun getBreakfastReview(

        date : String

    ) : ReviewModel

    suspend fun getLunchReview(

        date : String

    ) : ReviewModel

    suspend fun getDinnerReview(

        date : String

    ) : ReviewModel

    suspend fun postBreakfastReview(

        date : String,
        reviewDto: ReviewDto

    ) : Unit?

    suspend fun postLunchReview(

        date : String,
        reviewDto: ReviewDto

    ) : Unit?

    suspend fun postDinnerReview(

        date : String,
        reviewDto: ReviewDto

    ) : Unit?


}