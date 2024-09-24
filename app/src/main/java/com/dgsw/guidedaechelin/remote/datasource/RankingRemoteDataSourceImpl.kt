package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.MenuRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.RankingRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.RatingRemoteDataSource
import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import com.dgsw.guidedaechelin.remote.response.RankingResponseDto
import com.dgsw.guidedaechelin.remote.response.RatingResponseDto
import com.dgsw.guidedaechelin.remote.service.MenuService
import com.dgsw.guidedaechelin.remote.service.RankingService
import com.dgsw.guidedaechelin.remote.service.RatingService
import javax.inject.Inject

class RankingRemoteDataSourceImpl @Inject constructor(

    private val rankingService: RankingService

) : RankingRemoteDataSource{

    override suspend fun getRanking(mealType: String): RankingResponseDto {
        return rankingService.getRanking(mealType)
    }

}
