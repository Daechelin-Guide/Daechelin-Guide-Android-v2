package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.MenuRemoteDataSource
import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import com.dgsw.guidedaechelin.remote.service.MenuService
import javax.inject.Inject

class MenuRemoteDataSourceImpl @Inject constructor(

    private val menuService: MenuService

) : MenuRemoteDataSource{
    override suspend fun getMenu(date: String): MenuResponseDto {
        return menuService.getMenu(date)
    }

    override suspend fun getMenuDetail(date: String, mealType: String): MenuDetailResponseDto {
        return getMenuDetail(date, mealType)
    }


}
