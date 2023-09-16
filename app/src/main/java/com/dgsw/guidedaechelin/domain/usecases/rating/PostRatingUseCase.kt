package com.dgsw.guidedaechelin.domain.usecases.rating

import com.dgsw.guidedaechelin.domain.model.rating.RatingDto
import com.dgsw.guidedaechelin.domain.repository.meal.RatingRepository
import javax.inject.Inject

class PostRatingUseCase @Inject constructor(

    private val repository: RatingRepository

) {

    suspend operator fun invoke( ratingDto: RatingDto ) = kotlin.runCatching {
        repository.postRating(ratingDto)
    }

}