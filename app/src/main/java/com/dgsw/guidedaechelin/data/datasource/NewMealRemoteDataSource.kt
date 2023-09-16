package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.meal.NewMeal

interface NewMealRemoteDataSource {

    suspend fun getMenu(

        year : String,
        month : String,
        day : String

    ) : NewMeal

}