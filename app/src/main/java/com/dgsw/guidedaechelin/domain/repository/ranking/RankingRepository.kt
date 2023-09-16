package com.dgsw.guidedaechelin.domain.repository.ranking

import com.dgsw.guidedaechelin.domain.model.ranking.RankingModel
import javax.inject.Inject

interface RankingRepository{

    suspend fun getBreakfastRanking(

    ): RankingModel

    suspend fun getLunchRanking(

    ): RankingModel

    suspend fun getDinnerRanking(

    ): RankingModel

}