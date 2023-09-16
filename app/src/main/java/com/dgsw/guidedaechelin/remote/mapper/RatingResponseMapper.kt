package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.rating.RatingDto
import com.dgsw.guidedaechelin.remote.request.RatingRequest
import com.dgsw.guidedaechelin.remote.response.PostRatingResponse
import com.dgsw.guidedaechelin.remote.response.RatingResponse

internal fun RatingResponse.toModel() = Rating(

    star = this.star,
    menu = this.menu,
    date = this.date,
    review = 0
)

internal fun PostRatingResponse.toModel() = Rating(

    star = this.star,
    menu = this.menu,
    date = this.date,
    review = this.review
)

internal fun RatingDto.toModel() = RatingRequest(

    star = this.star,
    menus = this.menus

)