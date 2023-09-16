package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.RankingRemoteDataSource
import com.dgsw.guidedaechelin.remote.response.RankingBreakfastResponse
import com.dgsw.guidedaechelin.remote.response.RankingDinnerResponse
import com.dgsw.guidedaechelin.remote.response.RankingLunchResponse
import com.dgsw.guidedaechelin.remote.service.RankingService
import javax.inject.Inject

class RankingRemoteDataSourceImpl @Inject constructor(

    private val rankingService: RankingService

) : RankingRemoteDataSource {

    override suspend fun getBreakfastRanking(): RankingBreakfastResponse =
        rankingService.getBreakfastRanking()

    override suspend fun getLunchRanking(): RankingLunchResponse =
        rankingService.getLunchRanking()

    override suspend fun getDinnerRanking(): RankingDinnerResponse =
        rankingService.getDinnerRanking()
}