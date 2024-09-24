package com.dgsw.guidedaechelin.local.datasource

import com.dgsw.guidedaechelin.data.database.review.ReviewDao
//import com.dgsw.guidedaechelin.local.mapper.toEntity
import com.dgsw.guidedaechelin.presentation.utils.MealType
import javax.inject.Inject
//
//class ReviewLocalDataSourceImpl @Inject constructor(
//
//    private val reviewDao: ReviewDao
//
//) : ReviewLocalDataSource{
//
//    override suspend fun insertReview(localReview: LocalReview){
//        reviewDao.insert(localReview.toEntity())
//    }
//
//    override suspend fun isReviewed(date: String, mealType: MealType): Boolean =
//        reviewDao.isReviewed(date, mealType)
//
//}