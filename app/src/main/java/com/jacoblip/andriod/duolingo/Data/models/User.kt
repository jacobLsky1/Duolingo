package com.jacoblip.andriod.duolingo.Data.models

import android.graphics.drawable.Drawable
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.time.LocalDate
import java.util.*

open class User(
    @SerializedName("id") var id:String="",
    @SerializedName("name") var name:String="",
    @SerializedName("image") var image:String="",
    @SerializedName("email") var email:String = "",
    @SerializedName("userName") var userName:String= "",
    @SerializedName("passWord") var passWord:String= "",
    @SerializedName("xpPoints") var points:Int = 0,
    @SerializedName("level") var level:Int = 1,
    @SerializedName("numberOfLessonsPassed") var numberOfLessonsPassed:Int = -1,
    @SerializedName("canAccessLevel") var canAccessLevel:Int = 0,
    @SerializedName("dayStreak") var dayStreak:Int = 1,
    @SerializedName("isOnStreak") var isOnStreak:Boolean = true,
    @SerializedName("lastSignIn") var lastSignIn:String = "",
    @SerializedName("joinedDate") var joinedDate:String = "",
    @SerializedName("currentLearningLanguage") var currentLearningLanguage:String = "",
    @SerializedName("lesson") var lesson:Int = 1

):RealmObject(),Comparable<User> {

    override fun compareTo(other: User): Int {
        if(points<other.points)
            return 1
        return if(points==other.points)
            0
        else
            -1
    }
}