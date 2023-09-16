package com.dgsw.guidedaechelin.domain.repository.comment

import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.comment.CommentDto

interface CommentRepository {

    suspend fun postComment(

        commentDto: CommentDto

    ) : Comment


    suspend fun getComment(

        menu : String

    ) : List<Comment>





}