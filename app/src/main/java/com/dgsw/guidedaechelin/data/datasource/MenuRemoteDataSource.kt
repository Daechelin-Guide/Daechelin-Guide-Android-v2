package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto


interface MenuRemoteDataSource {

    suspend fun getMenu(

        date : String

    ) : MenuResponseDto

    suspend fun getMenuDetail (
        date: String,
        mealType: String
    ) : MenuDetailResponseDto

}