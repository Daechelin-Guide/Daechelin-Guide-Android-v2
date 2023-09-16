package com.dgsw.guidedaechelin.domain.usecases.meal

import com.dgsw.guidedaechelin.domain.repository.meal.MealRepository
import javax.inject.Inject

class GetBreakfastUseCase @Inject constructor(

    private val mealRepository: MealRepository

) {

    suspend operator fun invoke( date : String ) = kotlin.runCatching {
        mealRepository.getBreakfast(date)
    }

}