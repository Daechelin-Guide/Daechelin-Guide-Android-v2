package com.dgsw.guidedaechelin.domain.usecases.comment

import com.dgsw.guidedaechelin.domain.model.comment.CommentDto
import com.dgsw.guidedaechelin.domain.repository.comment.CommentRepository
import javax.inject.Inject

class PostCommentUseCase @Inject constructor(

    private val repository: CommentRepository

) {

    suspend operator fun invoke( commentDto: CommentDto ) = kotlin.runCatching {
        repository.postComment(commentDto)
    }

}