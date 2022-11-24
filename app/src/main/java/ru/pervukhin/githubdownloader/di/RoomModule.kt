package ru.pervukhin.githubdownloader.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.pervukhin.githubdownloader.data.retrofit.GitApi
import ru.pervukhin.githubdownloader.data.room.AppDataBase
import ru.pervukhin.githubdownloader.data.room.RepositoryDao
import javax.inject.Singleton


@Module
class RoomModule(val application: Application) {

    @Provides
    @Singleton
    fun provideRepositoryDao(roomDataBase: AppDataBase): RepositoryDao {
        return roomDataBase.repositoryDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase() : AppDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDataBase::class.java,
            "database"
        ).build()
    }
}