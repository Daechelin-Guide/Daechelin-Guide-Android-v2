package com.dgsw.guidedaechelin.data.database.review

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.dgsw.guidedaechelin.presentation.utils.MealType

@Dao
interface ReviewDao {

    @Insert(onConflict = REPLACE)
    fun insert(review : ReviewEntity)

    @Query("SELECT isReviewed FROM Review WHERE date = :date AND mealType = :mealType ")
    fun isReviewed(date : String, mealType: MealType) : Boolean



}