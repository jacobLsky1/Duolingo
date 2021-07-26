package com.jacoblip.andriod.duolingo.Data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

public open class Question(
        @SerializedName("englishSentence") var englishSentence:String = "",
        @SerializedName("hebrewSentence") var hebrewSentence:String = "",
        @SerializedName("xpPoints") var xpPoints:Int = 0,
        @SerializedName("answeredCorrect")var answeredCorrect:Boolean = false,
        @SerializedName("type") var type: QuestionType?=null,
        @SerializedName("picWord") var picWord:String? = null,
        @SerializedName("twinWords")  var twinWords:List<String>? = null

){
}