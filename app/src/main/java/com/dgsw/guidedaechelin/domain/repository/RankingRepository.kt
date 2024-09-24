package com.dgsw.guidedaechelin.domain.repository

import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RankingModel
import com.dgsw.guidedaechelin.presentation.utils.MealType

interface RankingRepository {

    suspend fun getRanking(

        mealType : MealType

    ) : RankingModel

}