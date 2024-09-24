package com.dgsw.guidedaechelin.remote.datasource

import com.dgsw.guidedaechelin.data.datasource.MenuRemoteDataSource
import com.dgsw.guidedaechelin.data.datasource.RatingRemoteDataSource
import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import com.dgsw.guidedaechelin.remote.response.RatingResponseDto
import com.dgsw.guidedaechelin.remote.service.MenuService
import com.dgsw.guidedaechelin.remote.service.RatingService
import javax.inject.Inject

class RatingRemoteDataSourceImpl @Inject constructor(

    private val ratingService: RatingService

) : RatingRemoteDataSource{

    override suspend fun getRating(menuId: Int): List<RatingResponseDto> {

        return ratingService.getRating(menuId)

    }

    override suspend fun postRating(menuId: Int, request : RatingRequestDto) {

        return ratingService.postRating(menuId, request)

    }

}
