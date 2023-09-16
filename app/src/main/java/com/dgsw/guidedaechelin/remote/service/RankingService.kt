package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface RankingService {

    @GET("/breakfast/ranking")
    suspend fun getBreakfastRanking(

    ): RankingBreakfastResponse

    @GET("/lunch/ranking")
    suspend fun getLunchRanking(

    ): RankingLunchResponse

    @GET("/dinner/ranking")
    suspend fun getDinnerRanking(

    ): RankingDinnerResponse


}