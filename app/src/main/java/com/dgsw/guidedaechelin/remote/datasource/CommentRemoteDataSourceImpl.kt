package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.CommentRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.comment.CommentDto
import com.dgsw.guidedaechelin.remote.mapper.toModel
import com.dgsw.guidedaechelin.remote.service.CommentService
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(

    private val commentService: CommentService

) : CommentRemoteDataSource{


    override suspend fun postComment(

        commentDto: CommentDto

    ): Comment =
        commentService.postComment(commentDto.toModel()).toModel()


    override suspend fun getComment(

        menu: String

    ): List<Comment> =
        commentService.getComment(menu).map { it.toModel() }


}