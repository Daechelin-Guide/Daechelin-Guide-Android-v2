package com.dgsw.guidedaechelin.remote.mapper

import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.comment.CommentDto
import com.dgsw.guidedaechelin.remote.request.CommentRequest
import com.dgsw.guidedaechelin.remote.response.CommentResponse

internal fun CommentResponse.toModel() = Comment(

    commentId = this.commentId,
    createdDate = this.createdDate,
    date = this.date,
    menu = this.menu,
    message = this.message

)

internal fun CommentDto.toModel() = CommentRequest(

    menu = this.menu,
    message = this.message

)