package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.request.ReviewRequest
import com.dgsw.guidedaechelin.remote.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewReviewService {

    @GET("/breakfast/review")
    suspend fun getBreakfastReview(

        @Query("date") date : String

    ) : ReviewResponse

    @GET("/lunch/review")
    suspend fun getLunchReview(

        @Query("date") date : String

    ) : ReviewResponse

    @GET("/dinner/review")
    suspend fun getDinnerReview(

        @Query("date") date : String

    ) : ReviewResponse

    @POST("/breakfast/review/create")
    suspend fun postBreakfastReview(
        @Query("date") date : String,
        @Body reviewRequest: ReviewRequest
    ) : Unit?

    @POST("/lunch/review/create")
    suspend fun postLunchReview(
        @Query("date") date : String,
        @Body reviewRequest: ReviewRequest
    ) : Unit?

    @POST("/dinner/review/create")
    suspend fun postDinnerReview(
        @Query("date") date : String,
        @Body reviewRequest: ReviewRequest
    ) : Unit?

}