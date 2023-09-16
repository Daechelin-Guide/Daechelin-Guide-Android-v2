package com.dgsw.guidedaechelin.presentation.features.home

import android.os.Parcelable
import com.dgsw.guidedaechelin.presentation.utils.MealType
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeToMealData(

    val mealType : MealType,
    val date : String,
    val menu : String

) : Parcelable
