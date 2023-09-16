package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.response.MealEachResponse
import com.dgsw.guidedaechelin.remote.response.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("/menu")
    suspend fun getMeal(

        @Query("year") year : String = "",
        @Query("month") month : String = "",
        @Query("day") day : String = ""

    ): MealResponse

    @GET("/lunch")
    suspend fun getLunch(

        @Query("date") date : String = "",

        ): MealEachResponse

    @GET("/break")
    suspend fun getBreakfast(

        @Query("date") date : String = "",

        ): MealEachResponse

    @GET("/dinner")
    suspend fun getDinner(

        @Query("date") date : String = "",

        ): MealEachResponse


}