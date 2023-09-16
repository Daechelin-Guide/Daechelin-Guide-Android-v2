package com.dgsw.guidedaechelin.data.repository.review

import com.dgsw.guidedaechelin.data.datasource.NewReviewRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.review.*
import com.dgsw.guidedaechelin.domain.repository.review.NewReviewRepository
import javax.inject.Inject

class NewReviewRepositoryImpl @Inject constructor(
    private val newReviewRemoteDataSource: NewReviewRemoteDataSource
) : NewReviewRepository{

    override suspend fun getBreakfastReview(date: String): ReviewModel =
        newReviewRemoteDataSource.getBreakfastReview(date)

    override suspend fun getLunchReview(date: String): ReviewModel =
        newReviewRemoteDataSource.getLunchReview(date)

    override suspend fun getDinnerReview(date: String): ReviewModel =
        newReviewRemoteDataSource.getDinnerReview(date)

    override suspend fun postBreakfastReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewRemoteDataSource.postBreakfastReview(date, reviewDto)


    override suspend fun postLunchReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewRemoteDataSource.postLunchReview(date, reviewDto)


    override suspend fun postDinnerReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewRemoteDataSource.postDinnerReview(date, reviewDto)


}