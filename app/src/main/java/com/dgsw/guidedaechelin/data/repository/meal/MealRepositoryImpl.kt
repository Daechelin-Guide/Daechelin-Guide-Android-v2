package com.dgsw.guidedaechelin.data.repository.meal

import com.dgsw.guidedaechelin.data.datasource.MealRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.meal.EachMeal
import com.dgsw.guidedaechelin.domain.model.meal.Meal
import com.dgsw.guidedaechelin.domain.repository.meal.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(

    private val remote : MealRemoteDataSource

) : MealRepository {

    override suspend fun getMeal(year: String, month: String, day: String): Meal =
        remote.getMeal(year, month, day)

    override suspend fun getLunch(date: String): EachMeal =
        remote.getLunch(date)

    override suspend fun getBreakfast(date: String): EachMeal =
        remote.getBreakfast(date)

    override suspend fun getDinner(date: String): EachMeal =
        remote.getDinner(date)

}