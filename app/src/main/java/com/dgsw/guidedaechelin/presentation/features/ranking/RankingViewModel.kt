package com.dgsw.guidedaechelin.presentation.features.ranking

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.RankingModel
import com.dgsw.guidedaechelin.domain.repository.RankingRepository
import com.dgsw.guidedaechelin.presentation.base.BaseViewModel
import com.dgsw.guidedaechelin.presentation.features.meal.MealViewModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.MutableEventFlow
import com.dgsw.guidedaechelin.presentation.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(

    private val rankingRepository: RankingRepository

) : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<RankingViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    lateinit var breakfastRanking : RankingModel
    lateinit var lunchRanking :  RankingModel
    lateinit var dinnerRanking : RankingModel

    var rankingList = mutableListOf<RankingModel>()


    private fun event(event : Event){

        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

//    fun getBreakfastRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            rankingRepository.getBreakfastRanking()
//        }.onSuccess {
//            event(Event.SuccessGetBreakfastRanking(it))
//        }.onFailure {
//            event(RankingViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun getLunchRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            rankingRepository.getLunchRanking()
//        }.onSuccess {
//            event(Event.SuccessGetLunchRanking(it))
//        }.onFailure {
//            event(RankingViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun getDinnerRanking() = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            rankingRepository.getDinnerRanking()
//        }.onSuccess {
//            event(Event.SuccessGetDinnerRanking(it))
//        }.onFailure {
//            event(RankingViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }

    fun getRanking(mealType: MealType) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{

            rankingRepository.getRanking(mealType)

        }.onSuccess {

            when(mealType){
                MealType.TYPE_BREAKFAST -> {
                    event(Event.SuccessGetBreakfastRanking(it))
                }
                MealType.TYPE_LUNCH -> {
                    event(Event.SuccessGetLunchRanking(it))
                }
                MealType.TYPE_DINNER -> {
                    event(Event.SuccessGetDinnerRanking(it))
                }
            }

        }.onFailure {
            event(Event.UnknownException)
            Log.d("오류","$it")
        }
    }
    sealed class Event {

        data class SuccessGetBreakfastRanking(val ranking : RankingModel) : Event()
        data class SuccessGetLunchRanking(val ranking : RankingModel) : Event()
        data class SuccessGetDinnerRanking(val ranking : RankingModel) : Event()
        object UnknownException : Event()
    }

}