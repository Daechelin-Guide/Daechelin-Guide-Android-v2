package com.dgsw.guidedaechelin.domain.model.review

data class ReviewModel(

    val totalStar : Float,
    val response : List<ResponseModel>
)

data class ResponseModel(

    val id : Int,
    val star : Float,
    val message : String,
    val menu : MenuModel

)

data class MenuModel(

    val date : String,
    val meal : String,
    val mealDate : String

)
