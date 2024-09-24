package com.dgsw.guidedaechelin.data.mapper

import android.media.Rating
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RatingItem
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.*


internal fun List<RatingResponseDto>.toModel() = RatingModel(
    rating = this.mapIndexed { index, value ->
        value.toModel(index)
    }

)

internal fun RatingResponseDto.toModel(index : Int) = RatingItem(
    id = index,
    comment = this.comment
)

internal fun RatingRequestModel.toDto() = RatingRequestDto(
    score = this.score,
    comment = this.comment
)














