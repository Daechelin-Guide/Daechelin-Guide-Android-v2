package com.dgsw.guidedaechelin.presentation.features.meal

import android.os.Parcelable
import com.dgsw.guidedaechelin.presentation.utils.MealType
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealToReview(

    val date : String,
    val menu : String,
    val mealType: MealType

) : Parcelable
