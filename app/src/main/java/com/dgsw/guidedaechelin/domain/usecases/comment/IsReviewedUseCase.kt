package com.dgsw.guidedaechelin.domain.usecases.comment

import com.dgsw.guidedaechelin.domain.repository.comment.LocalReviewRepository
import com.dgsw.guidedaechelin.presentation.utils.MealType
import javax.inject.Inject

class IsReviewedUseCase @Inject constructor(

    private val reviewRepository: LocalReviewRepository

) {

    suspend operator fun invoke( date : String, mealType: MealType ) =
        reviewRepository.isReviewed(date, mealType)

}