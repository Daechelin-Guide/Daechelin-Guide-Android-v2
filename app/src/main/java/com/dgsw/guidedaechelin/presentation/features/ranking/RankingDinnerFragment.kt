package com.dgsw.guidedaechelin.presentation.features.ranking

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentDinnerRankingBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingDinnerFragment : BaseFragment<FragmentDinnerRankingBinding,RankingSharedViewModel>(R.layout.fragment_dinner_ranking) {
    override val viewModel: RankingSharedViewModel by activityViewModels()

    private val rankingMenuAdapter : RankingMenuAdapter by lazy { RankingMenuAdapter() }

    override fun start() {

        Log.d("최희건", "RankingDinnerFragment - start() called")

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = rankingMenuAdapter
        }

        viewModel.getRanking(MealType.TYPE_DINNER)

        rankingMenuAdapter.setItemClickListener(object :  RankingMenuAdapter.OnItemClickListener{

            override fun onClick(mealType: MealType, date: String, menu: String) {
                onDinnerCommandListener.onDinner(mealType, date, menu)
//                val action = MealFragmentDirections.actionMealFragmentToReviewFragment(MealToReview(date, menu, mealType))
//                findNavController().navigate(action)
            }

        })

    }


    interface OnDinnerCommandListener {
        fun onDinner(mealType: MealType, date: String, menu : String)
    }

    fun setItemClickListener(onDinnerCommandListener: OnDinnerCommandListener) {
        this.onDinnerCommandListener = onDinnerCommandListener
    }

    private lateinit var onDinnerCommandListener : OnDinnerCommandListener


    override fun onResume() {
        setDinnerRanking()
        super.onResume()
    }

    fun setDinnerRanking(){
//        viewModel.dinnerRankingList.map { it.mealType = MealType.DINNER }
//
//        for ((index,value) in viewModel.dinnerRankingList.withIndex()){
//
//            value.index = index+1
//        }

        rankingMenuAdapter.submitList(viewModel.dinnerRankingList.toMutableList())
    }

    private fun handleEvent(event: RankingSharedViewModel.Event) =
        when (event) {
            is RankingSharedViewModel.Event.SuccessGetDinnerRanking -> {
                Log.d("최희건","dinner - ${viewModel.dinnerRankingList}")
                setDinnerRanking()
            }
            RankingSharedViewModel.Event.UnknownException -> {
                Log.d("최희건", "ERROR - $event")
            }
            else -> {}
        }

}