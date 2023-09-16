package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.meal.NewMeal
import com.dgsw.guidedaechelin.remote.response.NewMealResponse

internal fun NewMealResponse.toModel() = NewMeal(

    date = this.date,
    localDate = this.localDate,
    breakfast = this.breakfast,
    lunch = this.lunch,
    dinner = this.dinner

)