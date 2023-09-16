package com.dgsw.guidedaechelin.remote.response

data class RatingResponse(

    val star : Double,
    val menu : String,
    val date : String

)

data class PostRatingResponse(

    val star: Double,
    val menu: String,
    val date: String,
    val review : Int
)
