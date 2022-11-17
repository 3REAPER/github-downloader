package ru.pervukhin.githubdownloader.fragments.home

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import ru.pervukhin.githubdownloader.data.Service
import ru.pervukhin.githubdownloader.domain.Repository
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class HomeViewModel : ViewModel() {
    private val service = Service()
    val listRepositoryLiveData : MutableLiveData<List<Repository>> = MutableLiveData()
    val zipLiveData : MutableLiveData<String> = MutableLiveData()

    fun search(name: String) {

        viewModelScope.launch {
            listRepositoryLiveData.value = service.getRepositoryByUser(name).body()


        }
    }

    fun download(owner: String, name: String){
        viewModelScope.launch{
            val response = service.downloadZip("https://github.com/$owner/$name/archive/refs/heads/main.zip")
            response.body()?.let {
                launch(Dispatchers.IO){
                   saveFile(it, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator + "$name.zip")
                }
            }


        }
    }


    private fun saveFile(body: ResponseBody, pathWhereYouWantToSaveFile: String): String {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            //val file = File(getCacheDir(), "cacheFileAppeal.srl")
            val fos = FileOutputStream(pathWhereYouWantToSaveFile)
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