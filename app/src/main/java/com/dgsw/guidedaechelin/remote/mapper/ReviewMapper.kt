package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.review.*
import com.dgsw.guidedaechelin.remote.request.ReviewRequest
import com.dgsw.guidedaechelin.remote.response.*
import com.dgsw.guidedaechelin.remote.response.Menu
import com.dgsw.guidedaechelin.remote.response.Response


internal fun Menu.toModel() = MenuModel(

    meal = this.meal,
    date = this.date,
    mealDate = this.mealDate

)

internal fun Response.toModel() = ResponseModel(

    id = this.id,
    menu = this.menu.toModel(),
    star = this.star,
    message = this.message

)

internal fun ReviewResponse.toModel() = ReviewModel(

    totalStar = this.totalStar,
    response = this.response.map { it.toModel() }

)

internal fun ReviewDto.toRequest() = ReviewRequest(

    star = this.star,
    message = this.message

)

















