package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.rating.RatingDto

interface RatingRemoteDataSource {

    suspend fun getRating(

        menu : String

    ) : Rating

    suspend fun postRating(

        post : RatingDto

    ) : Rating


}