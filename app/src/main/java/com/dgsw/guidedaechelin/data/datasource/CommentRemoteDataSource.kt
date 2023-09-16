package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.comment.CommentDto

interface CommentRemoteDataSource {

    suspend fun postComment(

        commentDto: CommentDto

    ) : Comment

    suspend fun getComment(

        menu : String

    ) : List<Comment>


}