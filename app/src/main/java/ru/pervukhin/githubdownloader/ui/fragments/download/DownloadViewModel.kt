package ru.pervukhin.githubdownloader.ui.fragments.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.githubdownloader.App
import ru.pervukhin.githubdownloader.data.room.AppDataBase
import ru.pervukhin.githubdownloader.data.room.RepositoryDataBase
import javax.inject.Inject

class DownloadViewModel() : ViewModel() {
    val repositoryLiveData : MutableLiveData<List<RepositoryDataBase>> = MutableLiveData()

    @Inject
    lateinit var roomDataBase: AppDataBase
    init {
        App.appComponent.inject(this)
    }


    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = roomDataBase.repositoryDao().getAll()
            launch(Dispatchers.Main){
                repositoryLiveData.value = data
            }
        }
    }


}