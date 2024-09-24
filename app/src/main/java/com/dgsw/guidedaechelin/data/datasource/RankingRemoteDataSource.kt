package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import com.dgsw.guidedaechelin.remote.response.RankingResponseDto
import com.dgsw.guidedaechelin.remote.response.RatingResponseDto


interface RankingRemoteDataSource {

    suspend fun getRanking(

        mealType: String

    ) : RankingResponseDto

}