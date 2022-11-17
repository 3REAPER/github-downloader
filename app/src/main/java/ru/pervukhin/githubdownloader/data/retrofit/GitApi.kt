package ru.pervukhin.githubdownloader.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import retrofit2.http.Url
import ru.pervukhin.githubdownloader.domain.Repository

interface GitApi {

    @GET("users/{user}/repos")
    suspend fun getRepositoryByUser(@Path("user") user: String) : Response<List<Repository>>

    @Streaming
    @GET
    suspend fun downloadZip(@Url url: String): Response<ResponseBody>
}