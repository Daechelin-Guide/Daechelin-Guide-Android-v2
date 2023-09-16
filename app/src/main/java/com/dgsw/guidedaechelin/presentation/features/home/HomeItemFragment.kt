package com.dgsw.guidedaechelin.presentation.features.home

import androidx.fragment.app.viewModels
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentHomeItemBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment

class HomeItemFragment : BaseFragment<FragmentHomeItemBinding,HomeViewModel>(R.layout.fragment_home_item) {

    override val viewModel: HomeViewModel by viewModels()

    override fun start() {

    }

}