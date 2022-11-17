package ru.pervukhin.githubdownloader.ui.fragments.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pervukhin.githubdownloader.data.room.AppDataBase
import ru.pervukhin.githubdownloader.data.room.RepositoryDataBase

class DownloadViewModel() : ViewModel() {
    val repositoryLiveData : MutableLiveData<List<RepositoryDataBase>> = MutableLiveData()
    lateinit var appDataBase: AppDataBase

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = appDataBase.repositoryDao().getAll()
            launch(Dispatchers.Main){
                repositoryLiveData.value = data
            }
        }
    }


}