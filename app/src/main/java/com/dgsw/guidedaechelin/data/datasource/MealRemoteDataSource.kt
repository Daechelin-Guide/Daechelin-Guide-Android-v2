package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.meal.EachMeal
import com.dgsw.guidedaechelin.domain.model.meal.Meal


interface MealRemoteDataSource {

    suspend fun getMeal(
        year : String,
        month : String,
        day : String

    ): Meal

    suspend fun getLunch(

        date : String

        ): EachMeal

    suspend fun getBreakfast(

        date : String

        ): EachMeal

    suspend fun getDinner(

        date : String

        ): EachMeal

}