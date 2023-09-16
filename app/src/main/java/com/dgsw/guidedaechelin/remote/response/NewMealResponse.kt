package com.dgsw.guidedaechelin.remote.response

data class NewMealResponse(

    val date : String,
    val localDate: String,
    val breakfast: String?,
    val lunch: String?,
    val dinner : String?

)