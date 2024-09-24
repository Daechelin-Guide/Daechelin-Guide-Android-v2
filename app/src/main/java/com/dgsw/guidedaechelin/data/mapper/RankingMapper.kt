package com.dgsw.guidedaechelin.data.mapper

import android.media.Rating
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RankingItemModel
import com.dgsw.guidedaechelin.domain.model.RankingModel
import com.dgsw.guidedaechelin.domain.model.RatingItem
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.*


internal fun RankingResponseDto.toModel(mealType: MealType) = RankingModel(
    ranking = this.ranking.mapIndexed { index, rankingDto -> rankingDto.toModel(mealType,index+1) }
)

internal fun RankingDto.toModel(mealType: MealType, id : Int) = RankingItemModel(
    id = id,
    menu = this.menu,
    date = this.date,
    totalScore = this.totalScore?.toDouble(),
    mealType = mealType

)














