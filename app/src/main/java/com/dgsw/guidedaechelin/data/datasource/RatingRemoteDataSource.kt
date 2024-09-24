package com.dgsw.guidedaechelin.data.datasource

import com.dgsw.guidedaechelin.remote.request.RatingRequestDto
import com.dgsw.guidedaechelin.remote.response.MenuDetailResponseDto
import com.dgsw.guidedaechelin.remote.response.MenuResponseDto
import com.dgsw.guidedaechelin.remote.response.RatingResponseDto


interface RatingRemoteDataSource {

    suspend fun getRating(

        menuId : Int

    ) : List<RatingResponseDto>

    suspend fun postRating (

        menuId: Int,
        request : RatingRequestDto

    )

}