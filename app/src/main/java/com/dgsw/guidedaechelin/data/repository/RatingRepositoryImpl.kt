package com.dgsw.guidedaechelin.data.repository

import android.media.Rating
import com.dgsw.guidedaechelin.data.mapper.toDto
import com.dgsw.guidedaechelin.data.mapper.toModel
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.domain.model.RatingScoreModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.domain.repository.RatingRepository
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.service.MenuService
import com.dgsw.guidedaechelin.remote.service.RatingService
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val ratingService: RatingService,
    private val menuService: MenuService
) : RatingRepository{
    override suspend fun getRating(date : String, mealType: MealType): RatingScoreModel {
        val menu = menuService.getMenuDetail(date, mealType.name).toModel()
        val rating = ratingService.getRating(menu.id!!)

        return RatingScoreModel(score = menu.score!!, rating = rating.toModel().rating, id = menu.id )
    }

    override suspend fun postRating(menuId: Int, request: RatingRequestModel) {
        ratingService.postRating(menuId = menuId, request = request.toDto())
    }


}