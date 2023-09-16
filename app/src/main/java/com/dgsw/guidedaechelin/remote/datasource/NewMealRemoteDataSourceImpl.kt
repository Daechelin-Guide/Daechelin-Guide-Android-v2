package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.NewMealRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.meal.NewMeal
import com.dgsw.guidedaechelin.remote.mapper.toModel
import com.dgsw.guidedaechelin.remote.service.NewMealService
import javax.inject.Inject

class NewMealRemoteDataSourceImpl @Inject constructor(

    private val newMealService: NewMealService

) : NewMealRemoteDataSource{

    override suspend fun getMenu(year: String, month: String, day: String): NewMeal =
        newMealService.getMeal(year, month, day).toModel()
}