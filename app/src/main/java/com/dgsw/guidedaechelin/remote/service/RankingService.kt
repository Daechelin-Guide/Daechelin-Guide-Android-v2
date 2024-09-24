package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.response.RankingResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface RankingService {


    @GET("/ranking")
    suspend fun getRanking(
        @Query("mealType") mealType : String
    ) : RankingResponseDto


}

