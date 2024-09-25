package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReportService {

    @POST("/test")
    suspend fun reportReview(

    )

}