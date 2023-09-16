package com.dgsw.guidedaechelin.remote.response

data class MealResponse(

    val data : Data?

)

data class Data (

    val date : String,
    val breakfast : String?,
    val dinner : String?,
    val lunch : String?,
    val week : String
)

data class MealEachResponse(

    val meal : String?

)

