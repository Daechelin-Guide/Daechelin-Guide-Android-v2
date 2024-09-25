package com.dgsw.guidedaechelin.data.repository

import com.dgsw.guidedaechelin.domain.repository.ReportRepository
import com.dgsw.guidedaechelin.remote.service.ReportService
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportService: ReportService
) : ReportRepository  {

    override suspend fun reportReview(reviewId: Int) {
        reportService.reportReview()
    }
}