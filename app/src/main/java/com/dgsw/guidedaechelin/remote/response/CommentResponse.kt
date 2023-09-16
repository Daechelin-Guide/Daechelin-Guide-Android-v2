package com.dgsw.guidedaechelin.remote.response

data class CommentResponse(

    val commentId : Int,
    val message : String,
    val date : String,
    val menu : String,
    val createdDate : String

)
