package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuService {
    @GET("/menu")
    suspend fun getMenu(
        @Query("date") date : String
    ) : MenuResponseDto


    @GET("/menu/detail")
    suspend fun getMenuDetail(
        @Query("date") date: String,
        @Query("mealType") mealType : String
    ) : MenuDetailResponseDto

}

