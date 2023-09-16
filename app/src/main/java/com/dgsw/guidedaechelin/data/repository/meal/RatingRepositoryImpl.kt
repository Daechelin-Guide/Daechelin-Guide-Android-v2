package com.dgsw.guidedaechelin.data.repository.meal

import com.dgsw.guidedaechelin.data.datasource.RatingRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.rating.RatingDto
import com.dgsw.guidedaechelin.domain.repository.meal.RatingRepository
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(

    private val remote: RatingRemoteDataSource

) : RatingRepository {

    override suspend fun getRating(menu: String): Rating =
        remote.getRating(menu)

    override suspend fun postRating(ratingDto: RatingDto): Rating =
        remote.postRating(ratingDto)


}