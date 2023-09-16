package com.dgsw.guidedaechelin.presentation.features.ranking

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentLunchRankingBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingLunchFragment : BaseFragment<FragmentLunchRankingBinding,RankingSharedViewModel>(R.layout.fragment_lunch_ranking) {

    override val viewModel: RankingSharedViewModel by activityViewModels()

    private val rankingMenuAdapter : RankingMenuAdapter by lazy { RankingMenuAdapter() }

    override fun start() {

        Log.d("최희건","RankingLunchFragment - start() called")

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = rankingMenuAdapter
        }

        viewModel.getLunchRanking()

        rankingMenuAdapter.setItemClickListener(object :  RankingMenuAdapter.OnItemClickListener{

            override fun onClick(mealType: MealType, date: String, menu: String) {
                onLunchCommandListener.onLunch(mealType, date, menu)
//                val action = MealFragmentDirections.actionMealFragmentToReviewFragment(MealToReview(date, menu, mealType))
//                findNavController().navigate(action)
            }

        })

    }

    interface OnLunchCommandListener {
        fun onLunch(mealType: MealType, date: String, menu : String)
    }

    fun setItemClickListener(onLunchCommandListener: OnLunchCommandListener) {
        this.onLunchCommandListener = onLunchCommandListener
    }

    private lateinit var onLunchCommandListener : OnLunchCommandListener



    override fun onResume() {
        setLunchRanking()
        super.onResume()
    }

    fun setLunchRanking(){
        viewModel.lunchRankingList.map { it.mealType = MealType.LUNCH }

        for ((index,value) in viewModel.lunchRankingList.withIndex()){

            value.index = index+1
        }

        rankingMenuAdapter.submitList(viewModel.lunchRankingList.toMutableList())
    }

    private fun handleEvent(event: RankingSharedViewModel.Event) =
        when (event) {
            is RankingSharedViewModel.Event.SuccessGetLunchRanking -> {
                Log.d("최희건","lunch - ${viewModel.lunchRankingList}")
                setLunchRanking()
            }
            RankingSharedViewModel.Event.UnknownException -> {
                Log.d("최희건", "ERROR - $event")
            }

            else -> {}
        }
}