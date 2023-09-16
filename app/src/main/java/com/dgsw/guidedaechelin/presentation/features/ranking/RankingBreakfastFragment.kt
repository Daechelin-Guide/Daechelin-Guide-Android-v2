package com.dgsw.guidedaechelin.presentation.features.ranking

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentBreakfastRankingBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.features.meal.MealToReview
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingBreakfastFragment : BaseFragment<FragmentBreakfastRankingBinding,RankingSharedViewModel>(R.layout.fragment_breakfast_ranking) {
    override val viewModel: RankingSharedViewModel by activityViewModels()

    private val rankingMenuAdapter : RankingMenuAdapter by lazy { RankingMenuAdapter() }

    override fun start() {

        Log.d("최희건","RankingBreakfastFragment - start() called")

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = rankingMenuAdapter
        }

        viewModel.getBreakfastRanking()

        rankingMenuAdapter.setItemClickListener(object :  RankingMenuAdapter.OnItemClickListener{

            override fun onClick(mealType: MealType, date: String, menu: String) {
                onBreakfastCommandListener.onBreakfast(mealType, date, menu)
//                val action = MealFragmentDirections.actionMealFragmentToReviewFragment(MealToReview(date, menu, mealType))
//                findNavController().navigate(action)
            }

        })


    }

    interface OnBreakfastCommandListener {
        fun onBreakfast(mealType: MealType, date: String, menu : String)
    }

    fun setItemClickListener(onBreakfastCommandListener: OnBreakfastCommandListener) {
        this.onBreakfastCommandListener = onBreakfastCommandListener
    }

    private lateinit var onBreakfastCommandListener : OnBreakfastCommandListener


    fun setBreakfastRanking(){
        viewModel.breakfastRankingList.map { it.mealType = MealType.BREAKFAST }

        for ((index,value) in viewModel.breakfastRankingList.withIndex()){

            value.index = index+1
        }

        rankingMenuAdapter.submitList(viewModel.breakfastRankingList.toMutableList())
    }


    private fun handleEvent(event: RankingSharedViewModel.Event) =
        when (event) {
            is RankingSharedViewModel.Event.SuccessGetBreakfastRanking -> {
                Log.d("최희건","breakfast - ${viewModel.breakfastRankingList}")
                setBreakfastRanking()
            }

            RankingSharedViewModel.Event.UnknownException -> {
                Log.d("최희건", "ERROR - $event")
            }


            else -> {}
        }

}