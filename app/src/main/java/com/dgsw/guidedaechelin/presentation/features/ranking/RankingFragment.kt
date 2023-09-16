package com.dgsw.guidedaechelin.presentation.features.ranking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentRankingBinding
import com.dgsw.guidedaechelin.domain.model.ranking.RankingItemModel
import com.dgsw.guidedaechelin.domain.model.ranking.RankingModel
import com.dgsw.guidedaechelin.domain.model.review.MenuModel
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.features.home.HomeToMealData
import com.dgsw.guidedaechelin.presentation.features.home.ViewPagerAdapter
import com.dgsw.guidedaechelin.presentation.features.meal.MealFragmentDirections
import com.dgsw.guidedaechelin.presentation.features.meal.MealToReview
import com.dgsw.guidedaechelin.presentation.features.meal.MealViewModel
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import com.dgsw.guidedaechelin.remote.response.Menu
import com.dgsw.guidedaechelin.remote.response.RankingItem
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding,RankingViewModel>(R.layout.fragment_ranking){
    override val viewModel: RankingViewModel by viewModels()

    lateinit var rankingFragmentAdapter : RankingFragmentAdapter

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {

            Log.d("최희건","$position")

            when(position){

                0 -> {
                    binding.breakfastBtn.isSelected = true
                    binding.breakfastBtn.isChecked = true
                    Log.d("최희건", "$position")
                }
                1 -> {
                    binding.lunchBtn.isSelected = true
                    binding.lunchBtn.isChecked = true

                    Log.d("최희건", "$position")
                }
                2 -> {
                    binding.dinnerBtn.isSelected = true
                    binding.dinnerBtn.isChecked = true
                    Log.d("최희건", "$position")
                }

            }

            super.onPageSelected(position)
        }
    }


    override fun start() {

        rankingFragmentAdapter = RankingFragmentAdapter(this)

        rankingFragmentAdapter.setItemClickListener(object : RankingFragmentAdapter.OnCommandListener{
            override fun onCommand(mealType: MealType, date: String, menu: String) {

                val action = RankingFragmentDirections.actionRankingFragmentToMealFragment(
                    HomeToMealData(
                        mealType,
                        "${date.substring(0,4)}/${date.substring(4,6)}/${date.substring(6,8)}",
                        menu))
                findNavController().navigate(action)
            }


        })

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

//        binding.viewPagerContainer.adapter = rankingPageAdapter
        rankingFragmentAdapter.setFragment()
        binding.viewPagerContainer.adapter = rankingFragmentAdapter
        binding.viewPagerContainer.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerContainer.offscreenPageLimit = 3
        binding.viewPagerContainer.registerOnPageChangeCallback(pageChangeCallback)


        binding.breakfastBtn.setOnClickListener {
            binding.viewPagerContainer.setCurrentItem(0,true)
        }

        binding.lunchBtn.setOnClickListener {
            binding.viewPagerContainer.setCurrentItem(1,true)
        }

        binding.dinnerBtn.setOnClickListener {
            binding.viewPagerContainer.setCurrentItem(2,true)
        }



    }

    private fun printList(){
        for (i in viewModel.rankingList){
            Log.d("최희건", "list : ${i}")
        }
    }

    private fun handleEvent(event: RankingViewModel.Event) =
        when (event) {

            is RankingViewModel.Event.UnknownException -> Log.d("오류우우우우ㅜ웅", "ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR")
            is RankingViewModel.Event.SuccessGetBreakfastRanking -> {
                Log.d("최희건", "breakfast : ${event.ranking}")
                viewModel.breakfastRanking = event.ranking
                viewModel.rankingList.add(event.ranking)
                viewModel.getLunchRanking()
            }
            is RankingViewModel.Event.SuccessGetLunchRanking -> {
                Log.d("최희건", "lunch : ${event.ranking}")
                viewModel.lunchRanking = event.ranking
                viewModel.rankingList.add(event.ranking)
                viewModel.getDinnerRanking()
            }
            is RankingViewModel.Event.SuccessGetDinnerRanking -> {
                Log.d("최희건", "dinner : ${event.ranking}")
                viewModel.dinnerRanking = event.ranking
                viewModel.rankingList.add(event.ranking)
                printList()
//                rankingPageAdapter.submitList(viewModel.rankingList.toMutableList())
            }
        }

}