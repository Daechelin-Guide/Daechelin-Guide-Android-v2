package com.dgsw.guidedaechelin.presentation.features.setting

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentSettingBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding,SettingViewModel>(R.layout.fragment_setting) {

    override val viewModel: SettingViewModel by viewModels()

    override fun start() {
        binding.policyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_policyFragment)
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}