package com.dgsw.guidedaechelin.domain.repository

import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.domain.model.RatingScoreModel
import com.dgsw.guidedaechelin.presentation.utils.MealType

interface RatingRepository {

    suspend fun getRating(

        date : String,
        mealType: MealType

    ) : RatingScoreModel

    suspend fun postRating(

        menuId: Int,
        request : RatingRequestModel

    )

}