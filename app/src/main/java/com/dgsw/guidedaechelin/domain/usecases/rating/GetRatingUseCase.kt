package com.dgsw.guidedaechelin.domain.usecases.rating

import com.dgsw.guidedaechelin.domain.repository.meal.RatingRepository
import javax.inject.Inject

class GetRatingUseCase @Inject constructor(

    private val repository: RatingRepository

) {

    suspend operator fun invoke( menu : String ) = kotlin.runCatching {
        repository.getRating(menu)
    }

}