package com.dgsw.guidedaechelin.presentation.features.meal


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.rating.Rating
import com.dgsw.guidedaechelin.domain.model.review.*
import com.dgsw.guidedaechelin.domain.repository.comment.CommentRepository
import com.dgsw.guidedaechelin.domain.repository.comment.LocalReviewRepository
import com.dgsw.guidedaechelin.domain.repository.meal.RatingRepository
import com.dgsw.guidedaechelin.domain.repository.review.NewReviewRepository
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

    private val commentRepository : CommentRepository,
    private val ratingRepository : RatingRepository,
    private val localReviewRepository: LocalReviewRepository,
    private val newReviewRepository: NewReviewRepository

) : BaseViewModel(){

    private val _eventFlow = MutableEventFlow<MealViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    lateinit var date : String

    var reviewed = 0

//    fun getRating(menu : String) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            ratingRepository.getRating(menu)
//        }.onSuccess {
//            event(Event.SuccessGetRating(it))
//        }.onFailure {
//            event(MealViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun getReview(menu : String) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            commentRepository.getComment(menu)
//        }.onSuccess {
//            event(Event.SuccessGetComment(it))
//        }.onFailure {
//            event(MealViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }

    fun isReviewed(date : String, mealType: MealType) = viewModelScope.launch(Dispatchers.IO){

        kotlin.runCatching {
            localReviewRepository.isReviewed(date, mealType)
        }.onSuccess {
            event(Event.SuccessisReviewed(it))
        }.onFailure {
            event(Event.UnknownException)
            Log.d("오류","$it")
        }
    }

    fun getBreakfastReview(date : String) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            newReviewRepository.getBreakfastReview(date)
        }.onSuccess {
            event(Event.successGetReview(it))
        }.onFailure {
            event(MealViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }

    fun getLunchReview(date : String) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            newReviewRepository.getLunchReview(date)
        }.onSuccess {
            event(Event.successGetReview(it))
        }.onFailure {
            event(MealViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }

    fun getDinnerReview(date : String) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            newReviewRepository.getDinnerReview(date)
        }.onSuccess {
            event(Event.successGetReview(it))
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


        data class successGetReview(val review : ReviewModel) : Event()
//        data class SuccessGetRating(val rating : Rating) : Event()
//        data class SuccessGetComment(val comments : List<Comment>) : Event()
        data class SuccessisReviewed(val isReviewed : Boolean) : Event()
        object UnknownException : Event()
    }

}