package com.dgsw.guidedaechelin.presentation.features.ranking

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dgsw.guidedaechelin.presentation.utils.MealType

class RankingFragmentAdapter(fragment : Fragment) : FragmentStateAdapter(fragment){

    var fragmentList : ArrayList<Fragment> = ArrayList()
//    listOf<Fragment>(RankingBreakfastFragment(),RankingLunchFragment(),RankingDinnerFragment())

    fun setFragment(){
        val rankingBreakfastFragment = RankingBreakfastFragment()
        rankingBreakfastFragment.setItemClickListener(object : RankingBreakfastFragment.OnBreakfastCommandListener{

            override fun onBreakfast(mealType: MealType, date: String, menu: String) {
                onCommandListener.onCommand(mealType, date, menu)
            }

        })
        fragmentList.add(rankingBreakfastFragment)

        val rankingLunchFragment = RankingLunchFragment()
        rankingLunchFragment.setItemClickListener(object : RankingLunchFragment.OnLunchCommandListener{
            override fun onLunch(mealType: MealType, date: String, menu: String) {
                onCommandListener.onCommand(mealType, date, menu)
            }

        })
        fragmentList.add(rankingLunchFragment)

        val rankingDinnerFragment = RankingDinnerFragment()
        rankingDinnerFragment.setItemClickListener(object  : RankingDinnerFragment.OnDinnerCommandListener{
            override fun onDinner(mealType: MealType, date: String, menu: String) {
                onCommandListener.onCommand(mealType, date, menu)
            }

        })
        fragmentList.add(rankingDinnerFragment)
    }

    interface OnCommandListener {
        fun onCommand(mealType: MealType, date: String, menu : String)
    }

    fun setItemClickListener(onCommandListener: OnCommandListener) {
        this.onCommandListener = onCommandListener
    }

    private lateinit var onCommandListener : OnCommandListener

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}