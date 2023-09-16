package com.dgsw.guidedaechelin.domain.repository.review

import com.dgsw.guidedaechelin.domain.model.review.*
import com.dgsw.guidedaechelin.remote.request.ReviewRequest

interface NewReviewRepository {

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