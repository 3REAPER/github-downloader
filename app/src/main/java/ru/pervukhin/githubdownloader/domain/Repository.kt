package ru.pervukhin.githubdownloader.domain

import com.google.gson.annotations.SerializedName

class Repository(val id: Int, val name: String, val owner: Owner, val language: String, @SerializedName("created_at") val createdAt: String,@SerializedName("pushed_at") val pushedAt: String) {

}