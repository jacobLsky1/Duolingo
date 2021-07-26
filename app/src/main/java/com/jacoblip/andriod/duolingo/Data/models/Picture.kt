package com.jacoblip.andriod.duolingo.Data.models

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Picture(
        @SerializedName("name") var name:String = "",
        @SerializedName("url") var url:String = ""
):RealmObject() {
}