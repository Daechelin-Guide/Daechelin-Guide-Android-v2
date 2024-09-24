package com.dgsw.guidedaechelin.presentation.features.review

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.domain.repository.RatingRepository
import com.dgsw.guidedaechelin.presentation.base.BaseViewModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.MutableEventFlow
import com.dgsw.guidedaechelin.presentation.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(

    private val ratingRepository: RatingRepository

): BaseViewModel() {

    private val _eventFlow = MutableEventFlow<ReviewViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

//    fun postComment(comment: CommentDto) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            commentRepository.postComment(comment)
//        }.onSuccess {
//            event(ReviewViewModel.Event.SuccessPostComment(true))
//        }.onFailure {
//            event(ReviewViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun postRating(rating : RatingDto) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            ratingRepository.postRating(rating)
//        }.onSuccess {
//            event(ReviewViewModel.Event.SuccessPostRating(true))
//        }.onFailure {
//            event(ReviewViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }

//    fun postBreakfastReview(date : String, reviewDto: RatingRequestModel) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            newReviewRepository.postBreakfastReview(date, reviewDto)
//
//        }.onSuccess {
//            event(ReviewViewModel.Event.SuccessPostReview)
//        }.onFailure {
//            event(ReviewViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun postLunchReview(date : String, reviewDto: RatingRequestModel) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            newReviewRepository.postLunchReview(date, reviewDto)
//        }.onSuccess {
//            event(ReviewViewModel.Event.SuccessPostReview)
//        }.onFailure {
//            event(ReviewViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }
//
//    fun postDinnerReview(date : String, reviewDto: RatingRequestModel) = viewModelScope.launch(Dispatchers.IO) {
//
//        kotlin.runCatching{
//            newReviewRepository.postDinnerReview(date, reviewDto)
//        }.onSuccess {
//            event(ReviewViewModel.Event.SuccessPostReview)
//        }.onFailure {
//            event(ReviewViewModel.Event.UnknownException)
//            Log.d("오류","$it")
//        }
//    }

    fun postReview(menuId : Int, rating: RatingRequestModel) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
            ratingRepository.postRating(menuId, rating)
        }.onSuccess {
            event(ReviewViewModel.Event.SuccessPostReview)
        }.onFailure {
            event(ReviewViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }
    fun insertReview(date : String, mealType: MealType) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching{
//            localReviewRepository.insertReview(LocalReview(date,mealType,true))
        }.onSuccess {
            event(ReviewViewModel.Event.SuccessInsertReview)
        }.onFailure {
            event(ReviewViewModel.Event.UnknownException)
            Log.d("오류","$it")
        }
    }

    private fun event(event : Event){

        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object SuccessPostReview : Event()
        object SuccessInsertReview : Event()

//        data class SuccessPostComment(val succsess : Boolean) : Event()
//        data class SuccessPostRating(val succsess : Boolean) : Event()
        object UnknownException : Event()
    }

}