package com.dgsw.guidedaechelin.domain.usecases.comment

import com.dgsw.guidedaechelin.domain.repository.comment.CommentRepository
import javax.inject.Inject

class GetCommentUseCase @Inject constructor(

    private val repository: CommentRepository

) {

    suspend operator fun invoke( menu : String ) = kotlin.runCatching {
        repository.getComment(menu)
    }

}