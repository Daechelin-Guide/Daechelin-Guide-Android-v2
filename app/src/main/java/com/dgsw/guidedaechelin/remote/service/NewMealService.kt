package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.response.NewMealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewMealService {

    @GET("/menu")
    suspend fun getMeal(

        @Query("year") year : String = "",
        @Query("month") month : String = "",
        @Query("day") day : String = ""

    ): NewMealResponse



}