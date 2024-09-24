package com.dgsw.guidedaechelin.presentation.features.review

import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentReviewBinding
import com.dgsw.guidedaechelin.domain.model.RatingRequestModel
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.features.meal.MealToReview
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding,ReviewViewModel>(R.layout.fragment_review){

    override val viewModel: ReviewViewModel by viewModels()

    lateinit var mealToReview : MealToReview

    override fun start() {

        val args: ReviewFragmentArgs by navArgs()
        val _mealToReview = args.mealToReview
        mealToReview = _mealToReview

        Log.d("최희건", "date - ${mealToReview.date}")

        val date = mealToReview.date.replace("/","").substring(0,8)

        Log.d("최희건", "date - ${date}")

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        binding.reviewBtn.setOnClickListener {

            binding.reviewBtn.isEnabled = false

//            when(mealToReview.mealType){
//
//                MealType.BREAKFAST -> viewModel.postBreakfastReview(
//                    date,RatingRequestModel(binding.starRating.rating.toInt(),binding.reviewTxt.text.toString())
//                )
//                MealType.LUNCH -> viewModel.postLunchReview(
//                    date,RatingRequestModel(binding.starRating.rating.toInt(),binding.reviewTxt.text.toString())
//                )
//                MealType.DINNER -> viewModel.postDinnerReview(
//                    date,RatingRequestModel(binding.starRating.rating.toInt(),binding.reviewTxt.text.toString())
//                )
//            }

            viewModel.postReview(mealToReview.menuId, RatingRequestModel(binding.starRating.rating.toDouble(), binding.reviewTxt.text.toString()))

        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.reviewTxt.addTextChangedListener {
            binding.reviewCountTxt.text = binding.reviewTxt.text.toString().length.toString() + " / 50"
        }
    }


    private fun handleEvent(event: ReviewViewModel.Event) =
        when (event) {

            is ReviewViewModel.Event.SuccessInsertReview -> {
                findNavController().popBackStack()
            }

            is ReviewViewModel.Event.SuccessPostReview -> {
                viewModel.insertReview(mealToReview.date,mealToReview.mealType)
            }

//
//            is ReviewViewModel.Event.SuccessPostComment -> viewModel.postRating(RatingDto(
//                binding.starRating.rating.toDouble(),mealToReview.menu
//            ))
//
//            is ReviewViewModel.Event.SuccessPostRating -> {
//                viewModel.insertReview(mealToReview.date,mealToReview.mealType)
//            }

            is ReviewViewModel.Event.UnknownException -> Log.d("오류우우우우ㅜ웅", "ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR")

        }
}