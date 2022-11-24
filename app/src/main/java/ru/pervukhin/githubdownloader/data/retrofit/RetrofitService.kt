package ru.pervukhin.githubdownloader.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import ru.pervukhin.githubdownloader.App
import ru.pervukhin.githubdownloader.domain.Repository

class RetrofitService {

    private val gitApi: GitApi = App.appComponent.gitApi()

    suspend fun getRepositoryByUser(user: String): Response<List<Repository>>{
        return gitApi.getRepositoryByUser(user)
    }

    suspend fun downloadZip(url: String): Response<ResponseBody>{
        return gitApi.downloadZip(url)
    }
}