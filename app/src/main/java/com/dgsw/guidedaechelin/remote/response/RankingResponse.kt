package com.dgsw.guidedaechelin.remote.response

data class RankingBreakfastResponse(

    val response : List<RankingItem>

)

data class RankingLunchResponse(

    val response : List<RankingItem>

)

data class RankingDinnerResponse(

    val response : List<RankingItem>

)

data class RankingItem(

    val date : String,
    val star : Float,
    val menu : Menu

)
