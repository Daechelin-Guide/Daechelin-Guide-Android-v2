package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.RatingRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.rating.RatingDto
import com.dgsw.guidedaechelin.remote.mapper.toModel
import com.dgsw.guidedaechelin.remote.service.RatingService
import javax.inject.Inject

class RatingRemoteDataSourceImpl @Inject constructor(

    private val ratingService: RatingService

): RatingRemoteDataSource {

    override suspend fun getRating(

        menu: String

    ): Rating = ratingService.getRating(menu).toModel()

    override suspend fun postRating(

        post: RatingDto

    ): Rating =
        ratingService.postRating(post.toModel()).toModel()

}