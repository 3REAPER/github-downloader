package ru.pervukhin.githubdownloader.data

import okhttp3.ResponseBody
import retrofit2.Response
import ru.pervukhin.githubdownloader.data.RetrofitInstance
import ru.pervukhin.githubdownloader.domain.Repository

class Service {

    suspend fun getRepositoryByUser(user: String): Response<List<Repository>>{
            return RetrofitInstance.gitApi.getRepositoryByUser(user)
    }

    suspend fun downloadZip(url: String): Response<ResponseBody>{
        return RetrofitInstance.gitApi.downloadZip(url)
    }
}