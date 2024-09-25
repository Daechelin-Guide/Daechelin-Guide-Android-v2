package com.dgsw.guidedaechelin.domain.repository

interface ReportRepository {

    suspend fun reportReview(

        reviewId: Int

    )
}