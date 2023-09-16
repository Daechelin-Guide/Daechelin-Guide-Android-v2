package com.dgsw.guidedaechelin.domain.repository.meal

import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.rating.RatingDto

interface RatingRepository {

    suspend fun getRating(

        menu : String

    ) : Rating

    suspend fun postRating(

        ratingDto: RatingDto

    ) : Rating

}