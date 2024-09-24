package com.dgsw.guidedaechelin.domain.model

data class MenuModel(
    val breakfast : String?,
    val lunch : String?,
    val dinner : String?
)

data class MenuDetailModel(
    val id : Int?,
    val score : Double?
)
