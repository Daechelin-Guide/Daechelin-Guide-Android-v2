package com.dgsw.guidedaechelin.domain.model



data class RatingScoreModel(
    val id : Int,
    val score : Double,
    val rating: List<RatingItem>
)
data class RatingModel(
    val rating: List<RatingItem>
)

data class RatingItem(
    val id : Int,
    val comment : String
)
