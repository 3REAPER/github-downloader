package ru.pervukhin.githubdownloader.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pervukhin.githubdownloader.domain.Repository

interface GitApi {

    @GET("users/{user}/repos")
    suspend fun getRepositoryByUser(@Path("user") user: String) : Response<List<Repository>>
}