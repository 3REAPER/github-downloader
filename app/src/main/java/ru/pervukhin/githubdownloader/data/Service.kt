package ru.pervukhin.githubdownloader.data

import retrofit2.Response
import ru.pervukhin.githubdownloader.domain.Repository

class Service {

    suspend fun getRepositoryByUser(user: String): Response<List<Repository>>{
            return RetrofitInstance.gitApi.getRepositoryByUser(user)
    }
}