package com.jacoblip.andriod.duolingo.Data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

public class Lesson(
    @SerializedName("lessonNumber") var lessonNumber:Int = 0,
    @SerializedName("name") var name:String = "",
    @SerializedName("quetions") var quetions:List<Question> = listOf(),
    @SerializedName("xpPoints") var xpPoints:Int = 0,
    @SerializedName("canAccess") var canAccess:Boolean = false,
    @SerializedName("lessonCompleted") var lessonCompleted:Boolean = false,
    @SerializedName("studyStage") var studyStage:Int = 0

) {
}