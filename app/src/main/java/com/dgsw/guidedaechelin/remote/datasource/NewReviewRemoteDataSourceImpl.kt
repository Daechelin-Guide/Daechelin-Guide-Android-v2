package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.NewReviewRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.review.*
import com.dgsw.guidedaechelin.remote.mapper.toModel
import com.dgsw.guidedaechelin.remote.mapper.toRequest
import com.dgsw.guidedaechelin.remote.service.NewReviewService
import javax.inject.Inject

class NewReviewRemoteDataSourceImpl @Inject constructor(

    private val newReviewService: NewReviewService

) : NewReviewRemoteDataSource{

    override suspend fun getBreakfastReview(date: String): ReviewModel =
        newReviewService.getBreakfastReview(date).toModel()

    override suspend fun getLunchReview(date: String): ReviewModel =
        newReviewService.getLunchReview(date).toModel()

    override suspend fun getDinnerReview(date: String): ReviewModel =
        newReviewService.getDinnerReview(date).toModel()

    override suspend fun postBreakfastReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewService.postBreakfastReview(date, reviewDto.toRequest())

    override suspend fun postLunchReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewService.postLunchReview(date,reviewDto.toRequest())

    override suspend fun postDinnerReview(date: String, reviewDto: ReviewDto): Unit? =
        newReviewService.postDinnerReview(date,reviewDto.toRequest())
}