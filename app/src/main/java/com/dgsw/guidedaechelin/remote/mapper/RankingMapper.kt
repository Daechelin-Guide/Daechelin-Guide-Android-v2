package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.meal.Meal
import com.dgsw.guidedaechelin.domain.model.ranking.RankingItemModel
import com.dgsw.guidedaechelin.domain.model.ranking.RankingModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.response.*

internal fun RankingBreakfastResponse.toModel() = RankingModel(

    mealType = MealType.BREAKFAST,
    response = this.response.map{it.toModel()}
)

internal fun RankingLunchResponse.toModel() = RankingModel(

    mealType = MealType.LUNCH,
    response = this.response.map{it.toModel()}
)


internal fun RankingDinnerResponse.toModel() = RankingModel(

    mealType = MealType.DINNER,
    response = this.response.map{it.toModel()}
)


internal fun RankingItem.toModel() = RankingItemModel(

    index = 0,
    mealType = MealType.BREAKFAST,
    date = this.date,
    menu = this.menu.toModel(),
    star = this.star

)
