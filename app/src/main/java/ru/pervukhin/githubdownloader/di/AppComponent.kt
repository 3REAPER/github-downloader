package ru.pervukhin.githubdownloader.di

import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.pervukhin.githubdownloader.data.retrofit.GitApi
import ru.pervukhin.githubdownloader.data.room.AppDataBase
import ru.pervukhin.githubdownloader.data.room.RepositoryDao
import ru.pervukhin.githubdownloader.ui.activity.MainActivity
import ru.pervukhin.githubdownloader.ui.fragments.download.DownloadViewModel
import ru.pervukhin.githubdownloader.ui.fragments.home.HomeFragment
import javax.inject.Singleton


@Component(modules = [RetrofitModule::class, RoomModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(downloadViewModel: DownloadViewModel)

    fun gitApi(): GitApi
    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun gson():Gson
}