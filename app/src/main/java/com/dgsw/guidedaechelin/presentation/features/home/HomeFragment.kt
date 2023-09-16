package com.dgsw.guidedaechelin.presentation.features.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.FragmentHomeBinding
import com.dgsw.guidedaechelin.presentation.base.BaseFragment
import com.dgsw.guidedaechelin.presentation.utils.MealType
import com.dgsw.guidedaechelin.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home) {

    override val viewModel : HomeViewModel by viewModels()

    val viewPagerAdapter = ViewPagerAdapter()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect{ event -> handleEvent(event) }
        }

        setList()
        setListener()

        Log.d("최희건 viewModel.localDate =","${viewModel.localDate}")
    }

    override fun onPause() {
        Log.d("최희건", "onPause: ")
        viewModel.position = binding.viewPagerContainer.currentItem
        Log.d("최희건 viewModel.position","${viewModel.position}")
        super.onPause()
    }

    fun setListener(){

        binding.leftBtn.setOnClickListener {
            binding.viewPagerContainer.setCurrentItem(binding.viewPagerContainer.currentItem-1,true)
        }

        binding.rightBtn.setOnClickListener {
            binding.viewPagerContainer.setCurrentItem(binding.viewPagerContainer.currentItem+1,true)
        }

        binding.settingBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }

        binding.rankingBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_rankingFragment)
        }

        viewPagerAdapter.setItemClickListener(object : ViewPagerAdapter.OnItemClickListener{

            override fun onBreakfastClick(cal : Int, menu: String) {

                val date = viewModel.localDate.plusDays(cal.toLong())

                val action = HomeFragmentDirections.actionHomeFragmentToMealFragment(
                    HomeToMealData(
                        mealType = MealType.BREAKFAST,
                        date = "${date.toString().replace("-", "/")} (${getWeek(date)})",
                        menu = menu
                    ))
                findNavController().navigate(action)
            }

            override fun onLunchClick(cal : Int, menu: String) {

                val date = viewModel.localDate.plusDays(cal.toLong())

                val action = HomeFragmentDirections.actionHomeFragmentToMealFragment(
                    HomeToMealData(
                        mealType = MealType.LUNCH,
                        date = "${date.toString().replace("-", "/")} (${getWeek(date)})",
                        menu = menu
                    ))
                findNavController().navigate(action)
            }

            override fun onDinnerClick(cal : Int, menu: String) {

                val date = viewModel.localDate.plusDays(cal.toLong())

                val action = HomeFragmentDirections.actionHomeFragmentToMealFragment(
                    HomeToMealData(
                        mealType = MealType.DINNER,
                        date = "${date.toString().replace("-", "/")} (${getWeek(date)})",
                        menu = menu
                    ))
                findNavController().navigate(action)
            }

            override fun onVoteClick() {
                findNavController().navigate(R.id.action_homeFragment_to_rankingFragment)
            }
        })

        binding.dateTxt.setOnClickListener {

            val dialog = CalendarDialog(requireContext())
            dialog.showDialog()

            dialog.setOnClicklistener(object : CalendarDialog.OnDialogClickListener{
                override fun onClicked(date: LocalDate) {

                    binding.viewPagerContainer.currentItem =
                        10000+getDiffLocalDate(viewModel.localDate,date)

                }
            })

        }
    }

    fun setList(){

        binding.viewPagerContainer.adapter = viewPagerAdapter

        viewPagerAdapter.submitList(viewModel.list.toMutableList())

        binding.viewPagerContainer.setCurrentItem(viewModel.position,false)

        val todayDate = viewModel.localDate.plusDays(viewModel.list[binding.viewPagerContainer.currentItem].cal.toLong())
        val date = todayDate.toString()
        viewModel.getNewMeal(date.slice(IntRange(0,3)),date.slice(IntRange(5,6)),date.slice(IntRange(8,9)),binding.viewPagerContainer.currentItem)


        Log.d("최희건 - list ","${viewModel.list}")

        binding.viewPagerContainer.registerOnPageChangeCallback(pageChangeCallback)

        val changedLocalDate = viewModel.localDate.plusDays(
            viewModel.list[binding.viewPagerContainer.currentItem].cal.toLong())

        binding.dateTxt.text = "${changedLocalDate.toString().replace("-", "/")} (${getWeek(changedLocalDate)})"


    }

    fun getDiffLocalDate(a : LocalDate, b : LocalDate) : Int{

        val dataA = a.atStartOfDay()
        val dataB = b.atStartOfDay()

        val diff = Duration.between(dataA,dataB).toDays().toInt()
        Log.d("최희건", "diff - $diff")


        return diff

    }

    fun getWeek(date : LocalDate) : String{

        val week = date.dayOfWeek

        return week.getDisplayName(TextStyle.SHORT,Locale.KOREA)
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {

            val changedLocalDate = viewModel.localDate.plusDays(viewModel.list[position].cal.toLong())

            binding.dateTxt.text = "${changedLocalDate.toString().replace("-", "/")} (${getWeek(changedLocalDate)})"

            Log.d("최희건","position - $position, changeLocalDate - $changedLocalDate")

            val date = changedLocalDate.toString()

            if(viewModel.list[position].breakfast == "급식 정보 불러오는 중..." &&
                viewModel.list[position].lunch == "급식 정보 불러오는 중..." &&
                viewModel.list[position].dinner == "급식 정보 불러오는 중..." ){
                viewModel.getNewMeal(date.slice(IntRange(0,3)),date.slice(IntRange(5,6)),date.slice(IntRange(8,9)),position)

            }

            super.onPageSelected(position)
        }
    }

    private fun handleEvent(event: HomeViewModel.Event) =
        when (event) {
            is HomeViewModel.Event.SuccessGetNewMeal -> {
                Log.d("최희건 - getMeal", "${event.meal}")

                viewModel.list[event.position].breakfast = event.meal.breakfast ?: "급식 정보가 없습니다"
                viewModel.list[event.position].lunch = event.meal.lunch ?: "급식 정보가 없습니다"
                viewModel.list[event.position].dinner = event.meal.dinner ?: "급식 정보가 없습니다"

                viewPagerAdapter.submitList(viewModel.list.toMutableList())
                viewPagerAdapter.notifyItemChanged(event.position)

                Log.d("최희건 - 리스트 요소" ,"${viewModel.list}")
            }
            is HomeViewModel.Event.UnknownException -> Log.d("테스트", "EXCEPTION")
//            is HomeViewModel.Event.SuccessGetMeal -> {
//                Log.d("최희건 - getMeal", "${event.meal}")
//
//                viewModel.list[event.position].breakfast = event.meal.breakfast ?: "급식 정보가 없습니다"
//                viewModel.list[event.position].lunch = event.meal.lunch ?: "급식 정보가 없습니다"
//                viewModel.list[event.position].dinner = event.meal.dinner ?: "급식 정보가 없습니다"
//
//                viewPagerAdapter.submitList(viewModel.list.toMutableList())
//                viewPagerAdapter.notifyItemChanged(event.position)
//
//                Log.d("최희건 - 리스트 요소" ,"${viewModel.list}")
//            }

        }

}