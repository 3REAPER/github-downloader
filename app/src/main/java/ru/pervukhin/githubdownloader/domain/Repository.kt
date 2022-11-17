package ru.pervukhin.githubdownloader.domain

import com.google.gson.annotations.SerializedName

class Repository(
    val id: Int,
    val name: String,
    var description: String,
    val owner: Owner,
    var language: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val starCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("default_branch") val defaultBranch: String) {

}