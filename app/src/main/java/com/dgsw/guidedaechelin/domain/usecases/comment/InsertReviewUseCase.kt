package com.dgsw.guidedaechelin.domain.usecases.comment

import com.dgsw.guidedaechelin.domain.model.comment.LocalReview
import com.dgsw.guidedaechelin.domain.repository.comment.LocalReviewRepository
import javax.inject.Inject

class InsertReviewUseCase @Inject constructor(

    private val reviewRepository: LocalReviewRepository

) {

    suspend operator fun invoke(localReview : LocalReview ){
        reviewRepository.insertReview(localReview)
    }
}