package com.dgsw.guidedaechelin.domain.repository.meal

import com.dgsw.guidedaechelin.domain.model.meal.NewMeal

interface NewMealRepository {

    suspend fun getMenu(

        year : String,
        month : String,
        day : String

    ) : NewMeal
}