package com.dgsw.guidedaechelin.domain.usecases.meal

import com.dgsw.guidedaechelin.domain.repository.meal.MealRepository
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val mealRepository: MealRepository,
) {

    suspend operator fun invoke( year : String, day : String, month : String) = kotlin.runCatching {
        mealRepository.getMeal(year, month, day)
    }
}