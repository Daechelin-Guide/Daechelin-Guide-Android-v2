package com.dgsw.guidedaechelin.presentation.features.policy

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentPolicyBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment

class PolicyFragment : BaseFragment<FragmentPolicyBinding,PolicyViewModel>(R.layout.fragment_policy) {
    override val viewModel: PolicyViewModel by viewModels()

    override fun start() {

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.policyWeb.loadUrl("https://sites.google.com/view/daechelin/%ED%99%88")
    }


}