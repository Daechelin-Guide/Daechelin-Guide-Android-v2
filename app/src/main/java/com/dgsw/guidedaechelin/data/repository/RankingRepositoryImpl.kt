package com.dgsw.guidedaechelin.data.repository

import android.media.Rating
import android.service.notification.NotificationListenerService.Ranking
import com.dgsw.guidedaechelin.data.mapper.toDto
import com.dgsw.guidedaechelin.data.mapper.toModel
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.model.RankingModel
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.domain.model.RatingScoreModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.domain.repository.RankingRepository
import com.dgsw.guidedaechelin.domain.repository.RatingRepository
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.remote.service.MenuService
import com.dgsw.guidedaechelin.remote.service.RankingService
import com.dgsw.guidedaechelin.remote.service.RatingService
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val rankingService: RankingService
) : RankingRepository{

    override suspend fun getRanking(mealType: MealType): RankingModel {
        return rankingService.getRanking(mealType.name).toModel(mealType = mealType)
    }


}