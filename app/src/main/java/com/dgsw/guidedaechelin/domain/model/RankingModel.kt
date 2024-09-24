package com.dgsw.guidedaechelin.domain.model

import com.dgsw.guidedaechelin.presentation.utils.MealType

data class RankingModel(
    val ranking : List<RankingItemModel>
)

data class RankingItemModel(
    val id : Int?,
    val mealType : MealType?,
    val date : String?,
    val menu : String?,
    val totalScore : Double?
)
