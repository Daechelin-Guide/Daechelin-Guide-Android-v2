package com.dgsw.guidedaechelin.remote.service

import com.dgsw.guidedaechelin.remote.request.CommentRequest
import com.dgsw.guidedaechelin.remote.response.CommentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {

    @POST("/comment/regis")
    suspend fun postComment(

        @Body post : CommentRequest

    ) : CommentResponse



    @GET("/comment/message")
    suspend fun getComment(

        @Query("menu") menu : String

    ) : List<CommentResponse>
}