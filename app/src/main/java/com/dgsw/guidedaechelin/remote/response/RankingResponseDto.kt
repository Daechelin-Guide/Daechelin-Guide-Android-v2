package com.dgsw.guidedaechelin.remote.response

data class RankingResponseDto(
    val ranking : List<RankingDto>
)

data class RankingDto(
    val id : Int? = null,
    val menu : String? = null,
    val date : String? = null,
    val cal : String? = null,
    val totalScore : Double? = null,
    val ranking : Int? = null
)
