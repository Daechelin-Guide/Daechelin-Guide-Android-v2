package com.dgsw.guidedaechelin.local.mapper

import com.dgsw.guidedaechelin.data.database.review.ReviewEntity
import com.dgsw.guidedaechelin.domain.model.comment.LocalReview

internal fun LocalReview.toEntity() = ReviewEntity(

    date = this.date,
    mealType = this.mealType,
    isReviewed = this.isReviewed

)