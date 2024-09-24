package com.dgsw.guidedaechelin.data.mapper

import com.dgsw.guidedaechelin.domain.model.MenuDetailModel
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.remote.response.*


internal fun MenuResponseDto.toModel() = MenuModel(

    breakfast = this.breakfast,
    lunch = this.lunch,
    dinner = this.dinner

)

internal fun MenuDetailResponseDto.toModel() = MenuDetailModel(

    id = this.id,
    score = this.totalScore

)

















