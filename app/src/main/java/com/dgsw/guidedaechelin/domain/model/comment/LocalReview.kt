package com.dgsw.guidedaechelin.domain.model.comment

import com.dgsw.guidedaechelin.presentation.utils.MealType

data class LocalReview(

    var date : String,
    var mealType: MealType,
    var isReviewed : Boolean

)