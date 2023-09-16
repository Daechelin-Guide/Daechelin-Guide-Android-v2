package com.dgsw.guidedaechelin.presentation.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentHomeItemBinding
import com.dgsw.guidedaechelin.presentation.base.BaseListAdapter

class ViewPagerAdapter : BaseListAdapter<HomeItemData,FragmentHomeItemBinding>(R.layout.fragment_home_item) {

    override fun action(data: HomeItemData, binding: FragmentHomeItemBinding) {

        binding.breakfastFrame.setOnClickListener {
            itemClickListener.onBreakfastClick(data.cal,data.breakfast)
        }

        binding.lunchFrame.setOnClickListener {
            itemClickListener.onLunchClick(data.cal,data.lunch)
        }

        binding.dinnerFrame.setOnClickListener {
            itemClickListener.onDinnerClick(data.cal,data.dinner)
        }

        binding.voteFrame.setOnClickListener {
            itemClickListener.onVoteClick()
        }

        binding.dinnerFrame.isEnabled = false
        binding.breakfastFrame.isEnabled = false
        binding.lunchFrame.isEnabled = false

        binding.breakfastTxt.text = data.breakfast
        binding.lunchTxt.text = data.lunch
        binding.dinnerTxt.text = data.dinner

        if(data.breakfast != "급식 정보 불러오는 중..." && !data.breakfast.equals("급식 정보가 없습니다")){
            binding.breakfastFrame.isEnabled = true
        }
        if(data.dinner != "급식 정보 불러오는 중..." && !data.dinner.equals("급식 정보가 없습니다")){
            binding.dinnerFrame.isEnabled = true
        }
        if(data.lunch != "급식 정보 불러오는 중..." && !data.lunch.equals("급식 정보가 없습니다")){
            binding.lunchFrame.isEnabled = true
        }

    }


    interface OnItemClickListener {
        fun onBreakfastClick(cal : Int, menu : String)
        fun onLunchClick(cal : Int, menu : String)
        fun onDinnerClick(cal : Int, menu : String)
        fun onVoteClick()
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(FragmentHomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


}