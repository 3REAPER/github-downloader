package ru.pervukhin.githubdownloader.ui.fragments.home

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import ru.pervukhin.githubdownloader.App
import ru.pervukhin.githubdownloader.data.retrofit.RetrofitService
import ru.pervukhin.githubdownloader.data.room.RepositoryDao
import ru.pervukhin.githubdownloader.data.room.RepositoryDataBase
import ru.pervukhin.githubdownloader.domain.Repository
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    private val service = RetrofitService()
    val listRepositoryLiveData : MutableLiveData<List<Repository>> = MutableLiveData()
    val zipLiveData : MutableLiveData<String> = MutableLiveData()

    @Inject
    lateinit var repositoryDao: RepositoryDao
    init {
        App.appComponent.inject(this)
    }

    fun search(name: String) {

        viewModelScope.launch {
            listRepositoryLiveData.value = service.getRepositoryByUser(name).body()
        }
    }

    fun download(repository: Repository){
        viewModelScope.launch{
            val response = service.downloadZip("https://github.com/${repository.owner.login}/${repository.name}/archive/refs/heads/${repository.defaultBranch}.zip")
            if (response.isSuccessful) {
                response.body()?.let {
                    launch(Dispatchers.IO) {
                        var condition = saveFile(it,
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator + "${repository.name}.zip"
                        )
                        if (condition=="Success"){
                            repositoryDao.insert(RepositoryDataBase(0,repository.name, repository.owner.login))
                        }
                        launch(Dispatchers.Main){
                            zipLiveData.value = condition
                        }

                    }
                }
            }else{
                zipLiveData.value = "Error"
            }


        }
    }


    private fun saveFile(body: ResponseBody, path: String): String {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            if (File(path).exists()){
                return "Such file exists"
            }
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return "Success"
        }catch (e:Exception){
            Log.e("saveFile",e.toString())
        }
        finally {
            input?.close()
        }
        return "Error"
    }
}