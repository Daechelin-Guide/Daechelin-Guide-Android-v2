package com.dgsw.guidedaechelin.data.repository.ranking

import com.dgsw.guidedaechelin.data.datasource.RankingRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.ranking.RankingModel
import com.dgsw.guidedaechelin.domain.repository.ranking.RankingRepository
import com.dgsw.guidedaechelin.remote.mapper.toModel
import javax.inject.Inject

class RankingRespositoryImpl @Inject constructor(

    private val rankingRemoteDataSource: RankingRemoteDataSource

) : RankingRepository {

    override suspend fun getBreakfastRanking(): RankingModel =
        rankingRemoteDataSource.getBreakfastRanking().toModel()

    override suspend fun getLunchRanking(): RankingModel =
        rankingRemoteDataSource.getLunchRanking().toModel()

    override suspend fun getDinnerRanking(): RankingModel =
        rankingRemoteDataSource.getDinnerRanking().toModel()
}