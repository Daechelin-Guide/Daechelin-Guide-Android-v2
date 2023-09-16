package com.dgsw.guidedaechelin.data.repository.meal

import com.dgsw.guidedaechelin.data.datasource.NewMealRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.meal.NewMeal
import com.dgsw.guidedaechelin.domain.repository.meal.NewMealRepository
import javax.inject.Inject

class NewMealRepositoryImpl @Inject constructor(

    private val newMealRemoteDataSource: NewMealRemoteDataSource

) : NewMealRepository{

    override suspend fun getMenu(year: String, month: String, day: String): NewMeal =
        newMealRemoteDataSource.getMenu(year, month, day)
}