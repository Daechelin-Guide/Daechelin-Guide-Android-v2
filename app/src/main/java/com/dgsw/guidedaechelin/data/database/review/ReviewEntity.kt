package com.dgsw.guidedaechelin.data.database.review

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dgsw.guidedaechelin.presentation.utils.MealType


@Entity(tableName = "Review", primaryKeys = arrayOf("date", "mealType"))

class ReviewEntity(

    @ColumnInfo var date : String,
    @ColumnInfo var mealType : MealType,
    @ColumnInfo var isReviewed : Boolean = false

)
