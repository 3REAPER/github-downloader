package ru.pervukhin.githubdownloader

import android.app.Application
import ru.pervukhin.githubdownloader.di.AppComponent
import ru.pervukhin.githubdownloader.di.DaggerAppComponent
import ru.pervukhin.githubdownloader.di.RetrofitModule
import ru.pervukhin.githubdownloader.di.RoomModule

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .roomModule(RoomModule(application = this)
        ).build()
    }
}