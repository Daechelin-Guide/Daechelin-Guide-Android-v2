package com.dgsw.guidedaechelin.presentation.features.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.ItemReviewBinding
import com.dgsw.guidedaechelin.domain.model.RatingItem
import com.dgsw.guidedaechelin.domain.model.RatingModel
import com.dgsw.guidedaechelin.presentation.base.BaseListAdapter


class ReviewListAdapter : BaseListAdapter<RatingItem,ItemReviewBinding>(R.layout.item_review) {

    private val animalList = arrayListOf<AnimalData>().apply {
        add(AnimalData(R.drawable.group_191,"코끼리"))
        add(AnimalData(R.drawable.group_192,"고양이"))
        add(AnimalData(R.drawable.group_193,"꿀벌"))
        add(AnimalData(R.drawable.group_194,"토끼"))
        add(AnimalData(R.drawable.group_196,"고래"))
        add(AnimalData(R.drawable.group_197,"카멜레온"))
        add(AnimalData(R.drawable.group_198,"호랑이"))
        add(AnimalData(R.drawable.group_199,"뱀"))
        add(AnimalData(R.drawable.group_200,"코알라"))
        add(AnimalData(R.drawable.group_210,"여우"))
        add(AnimalData(R.drawable.group_211,"문어"))
        add(AnimalData(R.drawable.group_212,"해파리"))
        add(AnimalData(R.drawable.group_213,"강아지"))
        add(AnimalData(R.drawable.group_214,"오리"))
        add(AnimalData(R.drawable.group_215,"사자"))
        add(AnimalData(R.drawable.group_216,"상어"))
        add(AnimalData(R.drawable.group_217,"닭"))
        add(AnimalData(R.drawable.group_218,"소"))
        add(AnimalData(R.drawable.group_219,"티라노사우르스"))
        add(AnimalData(R.drawable.group_220,"오징어"))
        add(AnimalData(R.drawable.group_221,"거북이"))
        add(AnimalData(R.drawable.group_222,"꽃게"))
        add(AnimalData(R.drawable.group_223,"독수리"))
        add(AnimalData(R.drawable.group_224,"돼지"))
        add(AnimalData(R.drawable.group_225,"스테고사우르스"))
        add(AnimalData(R.drawable.group_226,"새우"))

    }

    private val adverbList = listOf<String>(
        "이쁜",
        "용감한",
        "길 잃은",
        "무서운",
        "거대한",
        "커다란",
        "조그만",
        "귀여운",
        "개발자",
        "AI",
        "로봇",
        "사나운",
        "잘생긴",
        "든든한",
        "소심한",
        "대담한",
        "별거 아닌",
        "지친",
        "자본주의",
        "그저 그런",
        "무거운",
        "가벼운",
        "어설픈",
        "자비로운",
        "너그러운",
        "상냥한",
        "친절한",
        "인싸",
        "아싸",
        "까다로운",
        "현명한",
        "멍청한",
        "편식쟁이",
        "미식가",
        "미슐랭",
        "순수한"

    )


    override fun action(data: RatingItem, binding: ItemReviewBinding) {

        binding.peopleIcon.background =binding.root.resources.getDrawable(animalList[encrypteAnimal(data.id)].drawable)
        binding.writerTxt.text = animalList[ encrypteAnimal(data.id) ].animal

        binding.adTxt.text = adverbList[ encrypteAdverb(data.id) ]

        binding.commentTxt.text = data.comment

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun encrypteAnimal(num : Int) : Int {

        return num*19345/123%animalList.size
    }
    private fun encrypteAdverb(num : Int) : Int {

        return num*19345/123%adverbList.size
    }
}
