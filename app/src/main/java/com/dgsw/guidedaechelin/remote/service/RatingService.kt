package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.RatingResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RatingService {


    @GET("/rating/{menu-id}")
    suspend fun getRating(
        @Path("menu-id") menuId : Int
    ) : List<RatingResponseDto>


    @POST("/rating/{menu-id}")
    suspend fun postRating(
        @Path("menu-id") menuId : Int,
        @Body request : RatingRequestDto
    )

}