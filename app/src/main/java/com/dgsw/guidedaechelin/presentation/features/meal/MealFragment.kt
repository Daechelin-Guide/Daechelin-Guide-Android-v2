package com.dgsw.guidedaechelin.presentation.features.meal

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentMealBinding
import com.dgsw.guidedaechelin.domain.model.RatingItem
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.features.home.HomeToMealData
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding,MealViewModel>(R.layout.fragment_meal){
    override val viewModel: MealViewModel by viewModels()

    lateinit var homeToMeal : HomeToMealData

    private val commentAdapter : ReviewListAdapter by lazy { ReviewListAdapter({
        reviewId ->  viewModel.reportReview(reviewId)
    }) }

//    private var commentDataSet = mutableListOf<Comment>()
    private var commentDataSet = mutableListOf<RatingItem>()

    var menuId : Int = 0


    override fun start() {

        val args: MealFragmentArgs by navArgs()
        val _homeToMeal = args.homeToMeal

        viewModel.reviewed = 2

        homeToMeal = _homeToMeal

        viewModel.date = homeToMeal.date.replace("/","").substring(0,8)

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        setSetting()

        viewModel.isReviewed(viewModel.date,homeToMeal.mealType)

        setMeal(homeToMeal.mealType,homeToMeal.menu)

        viewModel.getRating(homeToMeal.mealType, viewModel.date)

    }

    private fun setWriteButton(isReviewed : Boolean){

        if(isReviewed){
            viewModel.reviewed = 1
        }else{
            viewModel.reviewed = 2
        }
    }

    private fun setSetting(){

        binding.commentRecyclerview.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = commentAdapter
        }

        commentAdapter.submitList(commentDataSet)



        binding.mealTxt.text = "\n\n급식 정보를 불러오는 중입니다...\n\n"

        binding.dateTxt.text = getParsedDate(homeToMeal.date)


        binding.reviewBtn.setOnClickListener {

            when(viewModel.reviewed){
                0 -> {
                    Log.d("최희건", "연결 문제")
                    Toast.makeText(context,"서버 연결 오류",Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    Log.d("최희건", "이미 리뷰함")
                    Toast.makeText(context,"이미 리뷰하신 급식입니다",Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Log.d("최희건", "리뷰한 적 없음")
                    val action = MealFragmentDirections.actionMealFragmentToReviewFragment(MealToReview(viewModel.date,homeToMeal.menu,homeToMeal.mealType,menuId))
                    findNavController().navigate(action)
                }
            }

        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setMeal(mealType: MealType, menu : String){

        val index = when(mealType){
            MealType.TYPE_BREAKFAST -> 0
            MealType.TYPE_LUNCH -> 1
            MealType.TYPE_DINNER -> 2
        }

        val frameList : Array<Int> = arrayOf(R.drawable.orange_frame, R.drawable.green_frame,R.drawable.violet_frame)
        val textboxList : Array<Int> = arrayOf(R.drawable.orange_textbox, R.drawable.green_textbox,R.drawable.violet_textbox)
        val buttonList : Array<Int> = arrayOf(R.drawable.orange_button,R.drawable.green_button,R.drawable.violet_button)
        val mealTypeList : Array<String> = arrayOf("조식","중식","석식")

        binding.mealFrame.background = resources.getDrawable(frameList[index])
        binding.mealTypeTxt.background = resources.getDrawable(textboxList[index])
        binding.line.background = resources.getDrawable(textboxList[index])
        binding.reviewBtn.background = resources.getDrawable(buttonList[index])
        binding.mealTypeTxt.text = mealTypeList[index]
        binding.mealTxt.text = menu.replace(" ","\n")

    }

    private fun setRating(rating : Double){

        binding.starRating.rating = rating.toFloat()
    }

    private fun setReview(reviews : List<RatingItem>){
        commentDataSet = reviews.toMutableList()
        commentDataSet.removeIf{ x -> x.comment.isBlank() }
        commentAdapter.submitList(commentDataSet)
    }

    private fun getParsedDate(date : String) : String{

        var parsedDate = "${date.substring(0,4)}년 ${date.substring(5,7)}월 ${date.substring(8,10)}일"

        return parsedDate

    }

    private fun handleEvent(event: MealViewModel.Event) =
        when (event) {

            is MealViewModel.Event.SuccessGetRating -> {
                Log.d("최희건","reviews - ${event.rating.rating}")
                setReview(event.rating.rating)
                menuId = event.rating.id
                setRating(event.rating.score)
            }

            is MealViewModel.Event.SuccessIsReviewed -> {

                Log.d("최희건","isReviewed - ${event.isReviewed}")
                setWriteButton(event.isReviewed)

            }

            is MealViewModel.Event.SuccessReportReview -> {
                Toast.makeText(context,"신고가 정상적으로 접수되었습니다.", Toast.LENGTH_SHORT).show()
            }

            is MealViewModel.Event.UnknownException -> Log.d("오류우우우우ㅜ웅", "ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR")

        }


}