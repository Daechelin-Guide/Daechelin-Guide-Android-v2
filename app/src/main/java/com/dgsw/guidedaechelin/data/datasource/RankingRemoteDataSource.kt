package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.remote.response.RankingBreakfastResponse
import com.dgsw.guidedaechelin.remote.response.RankingDinnerResponse
import com.dgsw.guidedaechelin.remote.response.RankingLunchResponse
import retrofit2.http.GET

interface RankingRemoteDataSource {

    suspend fun getBreakfastRanking(

    ): RankingBreakfastResponse

    suspend fun getLunchRanking(

    ): RankingLunchResponse

    suspend fun getDinnerRanking(

    ): RankingDinnerResponse


}