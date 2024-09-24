package com.dgsw.guidedaechelin.presentation.features.ranking

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.ItemRankingBinding
import com.dgsw.guidedaechelin.databinding.PageRankingBinding
import com.dgsw.guidedaechelin.domain.model.RankingItemModel
import com.dgsw.guidedaechelin.presentation.base.BaseListAdapter
import com.dgsw.guidedaechelin.presentation.features.home.ViewPagerAdapter
import com.dgsw.guidedaechelin.presentation.features.meal.MealFragment
import com.dgsw.guidedaechelin.presentation.utils.MealType

class RankingMenuAdapter : BaseListAdapter<RankingItemModel,ItemRankingBinding>(R.layout.item_ranking) {

    lateinit var orangeFrame : Drawable
    lateinit var greenFrame : Drawable
    lateinit var violetFrame : Drawable


    override fun action(data: RankingItemModel, binding: ItemRankingBinding) {

        when(data.mealType!!){

            MealType.TYPE_BREAKFAST -> {
                binding.frame.background = orangeFrame
            }
            MealType.TYPE_LUNCH -> binding.frame.background = greenFrame
            MealType.TYPE_DINNER -> binding.frame.background = violetFrame

        }

        binding.titleTxt.text = "${data.id}"+"위 : "+ getParsedDate(data.date!!)
        binding.menuTxt.text = data.menu
        binding.starRating.rating = data.totalScore!!.toFloat()

        binding.frame.setOnClickListener {
            itemClickListener.onClick(data.mealType, data.date, data.menu!!)
        }

    }

    private fun getParsedDate(date : String) : String{

        var parsedDate = "${date.substring(0,4)}/${date.substring(4,6)}/${date.substring(6,8)}"

        return parsedDate

    }

    interface OnItemClickListener {
        fun onClick(mealType: MealType, date: String, menu : String)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        orangeFrame = parent.context.resources.getDrawable(R.drawable.orange_frame)
        greenFrame = parent.context.resources.getDrawable(R.drawable.green_frame)
        violetFrame = parent.context.resources.getDrawable(R.drawable.violet_frame)


        return BaseViewHolder(ItemRankingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}