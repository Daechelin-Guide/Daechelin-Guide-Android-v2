package com.dgsw.guidedaechelin.domain.model.ranking

import com.dgsw.guidedaechelin.domain.model.review.MenuModel
import com.dgsw.guidedaechelin.presentation.utils.MealType

data class RankingModel(

    var mealType: MealType,
    val response : List<RankingItemModel>
)

data class RankingItemModel(

    var index : Int,
    var mealType: MealType,
    val date : String,
    val star : Float,
    val menu : MenuModel

)