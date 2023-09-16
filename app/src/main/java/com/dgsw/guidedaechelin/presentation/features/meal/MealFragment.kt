package com.dgsw.guidedaechelin.presentation.features.meal

import android.util.Log
import android.widget.Toast
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentMealBinding
import com.dgsw.guidedaechelin.domain.model.comment.Comment
import com.dgsw.guidedaechelin.domain.model.review.ResponseModel
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.features.home.HomeToMealData
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding,MealViewModel>(R.layout.fragment_meal){
    override val viewModel: MealViewModel by viewModels()

    lateinit var homeToMeal : HomeToMealData

    private val commentAdapter : ReviewListAdapter by lazy { ReviewListAdapter() }

//    private var commentDataSet = mutableListOf<Comment>()
    private var commentDataSet = mutableListOf<ResponseModel>()



    override fun start() {

        val args: MealFragmentArgs by navArgs()
        val _homeToMeal = args.homeToMeal

        viewModel.reviewed = 0

        homeToMeal = _homeToMeal

        viewModel.date = homeToMeal.date.replace("/","").substring(0,8)

        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        setSetting()

        viewModel.isReviewed(viewModel.date,homeToMeal.mealType)

        setMeal(homeToMeal.mealType,homeToMeal.menu)

        when(homeToMeal.mealType){
            MealType.BREAKFAST -> {
                viewModel.getBreakfastReview(viewModel.date)
            }
            MealType.LUNCH -> {
                viewModel.getLunchReview(viewModel.date)

            }
            MealType.DINNER -> {
                viewModel.getDinnerReview(viewModel.date)
            }
        }

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
                    val action = MealFragmentDirections.actionMealFragmentToReviewFragment(MealToReview(viewModel.date,homeToMeal.menu,homeToMeal.mealType))
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
            MealType.BREAKFAST -> 0
            MealType.LUNCH -> 1
            MealType.DINNER -> 2
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
        binding.mealTxt.text = menu.replace(",","\n")

    }

    private fun setRating(rating : Double){

        binding.starRating.rating = rating.toFloat()
    }

    private fun setReview(reviews : List<ResponseModel>){
        commentDataSet = reviews.toMutableList()
        commentDataSet.removeIf{ x -> x.message.isBlank() }
        commentAdapter.submitList(commentDataSet)
    }

    private fun getParsedDate(date : String) : String{

        var parsedDate = "${date.substring(0,4)}년 ${date.substring(5,7)}월 ${date.substring(8,10)}일"

        return parsedDate

    }

    private fun handleEvent(event: MealViewModel.Event) =
        when (event) {

            is MealViewModel.Event.successGetReview -> {
                Log.d("최희건","reviews - ${event.review.response}")
                setReview(event.review.response)
                setRating(event.review.totalStar.toDouble())
            }

            is MealViewModel.Event.SuccessisReviewed -> {

                Log.d("최희건","isReviewed - ${event.isReviewed}")
                setWriteButton(event.isReviewed)

            }

            is MealViewModel.Event.UnknownException -> Log.d("오류우우우우ㅜ웅", "ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR")

        }


}