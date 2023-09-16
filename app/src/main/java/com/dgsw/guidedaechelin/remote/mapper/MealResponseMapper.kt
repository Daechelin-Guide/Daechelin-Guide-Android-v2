package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.meal.EachMeal
import com.dgsw.guidedaechelin.domain.model.meal.Meal
import com.dgsw.guidedaechelin.remote.response.MealEachResponse
import com.dgsw.guidedaechelin.remote.response.MealResponse

internal fun MealResponse.toModel() = Meal(

    date = data!!.date,
    breakfast = data.breakfast,
    lunch = data.lunch,
    dinner = data.dinner,
    week = data.week
)

internal fun MealEachResponse.toModel() = EachMeal(

    meal = this.meal

)




