package com.dgsw.guidedaechelin.presentation.features.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.presentation.base.BaseViewModel
import com.dgsw.guidedaechelin.presentation.utils.MutableEventFlow
import com.dgsw.guidedaechelin.presentation.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val menuRepository: MenuRepository

) : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    var localDate = LocalDate.now()

    var list = mutableListOf<HomeItemData>()
    
    var position = 10000

    init {

        Log.d("최희건", "HomeViewModel 생성됨")

        list.clear()
        list.add(HomeItemData(0,"급식 정보 불러오는 중...","급식 정보 불러오는 중...","급식 정보 불러오는 중..."))

        for (i in 1..10000){
            list.add(HomeItemData(i,"급식 정보 불러오는 중...","급식 정보 불러오는 중...","급식 정보 불러오는 중..."))
        }
        for (i in 1..10000){
            list.add(0,HomeItemData(-i,"급식 정보 불러오는 중...","급식 정보 불러오는 중...","급식 정보 불러오는 중..."))
        }
    }

    fun getMenu(date : String, position : Int) = viewModelScope.launch(Dispatchers.IO) {

        kotlin.runCatching {
            menuRepository.getMenu(date)
        }.onSuccess {
            event(Event.SuccessGetNewMeal(it,position))
        }.onFailure {
            event(Event.UnknownException)
            Log.d("오류","$it")
        }
    }


    private fun event(event : Event){

        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SuccessGetNewMeal(val meal : MenuModel, val position : Int) : Event()
        object UnknownException : Event()
    }

}