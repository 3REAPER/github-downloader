package ru.pervukhin.githubdownloader.domain

import android.graphics.Color
import com.google.gson.annotations.SerializedName

class Repository(
    val id: Int,
    val name: String,
    var description: String,
    val owner: Owner,
    var language: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("pushed_at") val pushedAt: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val starCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int) {

    fun getColorByLanguage(): Int{
        when(language){
            "Java" -> return Color.parseColor("#e35720")
            "Kotlin" -> return Color.parseColor("#ec47f5")
            "C#" -> return Color.parseColor("#6250eb")
            "C++" -> return Color.parseColor("#321aeb")
            "Html" -> return Color.parseColor("#ab2626")
            "CSS" -> return Color.parseColor("#30c5f2")
            "Python" -> return Color.parseColor("#7fed1f")
        }
        return Color.parseColor("#000000")
    }

    fun getDateCreate(): String{
        val dateRaw = createdAt.split("T")[0].split("-")
        return dateRaw[0] + "." +dateRaw[2] +"." +dateRaw[1]
    }

    fun getDatePush(): String{
        val dateRaw = pushedAt.split("T")[0].split("-")
        return dateRaw[0] + "." +dateRaw[2] +"." +dateRaw[1]
    }

    fun getTimeCreate(): String{
        val time = createdAt.split("T")[1].split(":")
        return time[0] +":" + time[1]
    }

    fun getTimePush(): String{
        val time = pushedAt.split("T")[1].split(":")
        return time[0] +":" + time[1]
    }

}