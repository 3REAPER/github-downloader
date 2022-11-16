package ru.pervukhin.githubdownloader.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.pervukhin.githubdownloader.data.Service
import ru.pervukhin.githubdownloader.domain.Repository

class HomeViewModel : ViewModel() {
    private val service = Service()
    val liveData: MutableLiveData<Response<List<Repository>>> = MutableLiveData()

    fun search(name: String) {
        viewModelScope.launch {
            liveData.value = service.getRepositoryByUser(name)
        }
    }

}