package com.dgsw.guidedaechelin.presentation.features.meal


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.RatingScoreModel
import com.dgsw.guidedaechelin.domain.repository.RatingRepository
import com.dgsw.guidedaechelin.domain.repository.ReportRepository
import com.dgsw.guidedaechelin.presentation.base.BaseViewModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.MutableEventFlow
import com.dgsw.guidedaechelin.presentation.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(

    private val ratingRepository: RatingRepository,
    private val reportRepository: ReportRepository

) : BaseViewModel(){

    private val _eventFlow = MutableEventFlow<MealViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    lateinit var date : String

    var reviewed = 2

    fun isReviewed(date : String, mealType: MealType) = viewModelScope.launch(Dispatchers.IO){}

    fun getRating(mealType: MealType, date : String) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            ratingRepository.getRating(date, mealType)
        }.onSuccess {
            event(Event.SuccessGetRating(it))
        }.onFailure {
            event(MealViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }

    fun reportReview(reviewId : Int) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            reportRepository.reportReview(reviewId)
        }.onSuccess {
            event(Event.SuccessReportReview)
        }.onFailure {
            event(MealViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }


    private fun event(event : Event){

        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {


        data class SuccessGetRating(val rating : RatingScoreModel) : Event()
        data class SuccessIsReviewed(val isReviewed : Boolean) : Event()
        object SuccessReportReview : Event()
        object UnknownException : Event()
    }

}