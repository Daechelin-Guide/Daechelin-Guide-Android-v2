package com.dgsw.guidedaechelin.data.repository.comment

import com.dgsw.guidedaechelin.data.datasource.CommentRemoteDataSource
import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.comment.CommentDto
import com.dgsw.guidedaechelin.domain.repository.comment.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(

    private val remoteDataSource: CommentRemoteDataSource

) : CommentRepository {

    override suspend fun postComment(commentDto: CommentDto): Comment =
        remoteDataSource.postComment(commentDto)


    override suspend fun getComment(menu: String): List<Comment> =
        remoteDataSource.getComment(menu)


}