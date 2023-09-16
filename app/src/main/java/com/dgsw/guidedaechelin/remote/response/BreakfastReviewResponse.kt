package com.dgsw.guidedaechelin.remote.response

data class ReviewResponse(

    val totalStar : Float,
    val response : List<Response>

)

data class Response(

    val id : Int,
    val star : Float,
    val message : String,
    val menu : Menu
)

data class Menu(

    val date : String,
    val meal : String,
    val mealDate : String

)