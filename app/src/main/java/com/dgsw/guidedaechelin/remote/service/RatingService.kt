package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.request.RatingRequest
import com.dgsw.guidedaechelin.remote.response.PostRatingResponse
import com.dgsw.guidedaechelin.remote.response.RatingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RatingService {

    @GET("/star")
    suspend fun getRating(

        @Query("menu") menu : String

    ): RatingResponse

    @POST("/regis")
    suspend fun postRating(

        @Body post : RatingRequest

    ) : PostRatingResponse

}