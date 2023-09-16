package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.MealRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.meal.EachMeal
import com.dgsw.guidedaechelin.domain.model.meal.Meal
import com.dgsw.guidedaechelin.remote.mapper.toModel
import com.dgsw.guidedaechelin.remote.service.MealService
import javax.inject.Inject

class MealRemoteDataSourceImpl @Inject constructor(

    private val mealService: MealService

) : MealRemoteDataSource{

    override suspend fun getMeal(year: String, month: String, day: String): Meal =
        mealService.getMeal(year, month, day).toModel()


    override suspend fun getLunch(date: String): EachMeal =
        mealService.getLunch(date).toModel()


    override suspend fun getBreakfast(date: String): EachMeal =
        mealService.getBreakfast(date).toModel()

    override suspend fun getDinner(date: String): EachMeal =
        mealService.getDinner(date).toModel()


}