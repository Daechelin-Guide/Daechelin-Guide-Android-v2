package com.dgsw.guidedaechelin.presentation.features.ranking

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.RankingItemModel
import com.dgsw.guidedaechelin.domain.model.RankingModel
import com.dgsw.guidedaechelin.domain.repository.RankingRepository
import com.dgsw.guidedaechelin.presentation.base.BaseViewModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.MutableEventFlow
import com.dgsw.guidedaechelin.presentation.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingSharedViewModel @Inject constructor(

    private val rankingRepository: RankingRepository

): BaseViewModel() {

    private val _eventFlow = MutableEventFlow<RankingSharedViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    lateinit var breakfastRankingList : MutableList<RankingItemModel>
    lateinit var lunchRankingList : MutableList<RankingItemModel>
    lateinit var dinnerRankingList : MutableList<RankingItemModel>

    private fun event(event : RankingSharedViewModel.Event){

        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun getRanking(mealType: MealType) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{

            rankingRepository.getRanking(mealType)

        }.onSuccess {

            when(mealType){
                MealType.TYPE_BREAKFAST -> {
                    breakfastRankingList = it.ranking.toMutableList()
                    event(RankingSharedViewModel.Event.SuccessGetBreakfastRanking(it))
                }
                MealType.TYPE_LUNCH -> {
                    lunchRankingList = it.ranking.toMutableList()
                    event(RankingSharedViewModel.Event.SuccessGetLunchRanking(it))
                }
                MealType.TYPE_DINNER -> {
                    dinnerRankingList = it.ranking.toMutableList()
                    event(RankingSharedViewModel.Event.SuccessGetDinnerRanking(it))
                }
            }

        }.onFailure {
            event(RankingSharedViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }

//    fun getBreakfastRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//
//            rankingRepository.getRanking(MealType.BREAKFAST)
//
//        }.onSuccess {
//
//            breakfastRankingList = it.ranking.toMutableList()
//
//
//
//            event(RankingSharedViewModel.Event.SuccessGetBreakfastRanking(it))
//
//        }.onFailure {
//            event(RankingSharedViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun getLunchRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//
//            rankingRepository.getLunchRanking()
//
//        }.onSuccess {
//
////            lunchRankingList = it.response.toMutableList()
////
////            lunchRankingList.map { it.mealType = MealType.LUNCH }
////
////            for ((index,value) in lunchRankingList.withIndex()){
////
////                value.index = index+1
////            }
//
//            event(RankingSharedViewModel.Event.SuccessGetLunchRanking(it))
//
//        }.onFailure {
//            event(RankingSharedViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun getDinnerRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//
//            rankingRepository.getDinnerRanking()
//
//        }.onSuccess {
//
//            dinnerRankingList = it.response.toMutableList()
//
//            dinnerRankingList.map { it.mealType = MealType.DINNER }
//
//            for ((index,value) in dinnerRankingList.withIndex()){
//
//                value.index = index+1
//            }
//
//            event(RankingSharedViewModel.Event.SuccessGetDinnerRanking(it))
//
//        }.onFailure {
//            event(RankingSharedViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }



    sealed class Event {

        data class SuccessGetBreakfastRanking(val ranking : RankingModel) : Event()
        data class SuccessGetLunchRanking(val ranking : RankingModel) : Event()
        data class SuccessGetDinnerRanking(val ranking : RankingModel) : Event()
        object UnknownException : Event()
    }
}