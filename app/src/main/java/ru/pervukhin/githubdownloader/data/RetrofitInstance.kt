package ru.pervukhin.githubdownloader.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder().build()

        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))// Перевести результат запроса через Gson
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    }

    val gitApi: GitApi by lazy {
        retrofit.create(GitApi::class.java)
    }
}