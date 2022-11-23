package ru.pervukhin.githubdownloader.ui.fragments.home

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.pervukhin.githubdownloader.ui.activity.MainActivity
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.ui.adapter.RepositoryFindAdapter
import ru.pervukhin.githubdownloader.data.room.AppDataBase
import ru.pervukhin.githubdownloader.data.room.RepositoryDao
import ru.pervukhin.githubdownloader.data.room.RepositoryDataBase
import ru.pervukhin.githubdownloader.domain.Repository


class HomeFragment : Fragment(), RepositoryFindAdapter.RepositoryClick {

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val name = view.findViewById<TextInputEditText>(R.id.name)
        val nameLayout = view.findViewById<TextInputLayout>(R.id.name_field)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_repository)
        val adapter = RepositoryFindAdapter(this)
        recyclerView.adapter = adapter

        nameLayout.setEndIconOnClickListener {
            if(mainActivity.isNetworkAvailable(requireContext())) {
                viewModel.search(name.text.toString())
            }else{
                Toast.makeText(context,R.string.internet,Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.listRepositoryLiveData.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter.setList(list)
            } else {
                Toast.makeText(context, R.string.not_found, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.zipLiveData.observe(viewLifecycleOwner) {
            if (it.contains("Success")) {
                val condition = it.split(".")
                Toast.makeText(context, R.string.download_success, Toast.LENGTH_SHORT).show()
                saveData(condition[1], condition[2])
            }

            when (it) {
                "Error" -> Toast.makeText(context, R.string.download_error, Toast.LENGTH_SHORT).show()
                "Such file exists" -> Toast.makeText(context, R.string.file_exists, Toast.LENGTH_SHORT).show()

            }

        }

        return view
    }

    private fun saveData(name: String, owner: String) {
        runBlocking {
            launch(Dispatchers.IO) {
                context?.let {
                    val service = context?.let { AppDataBase.getDatabase(it).repositoryDao() }!!
                    if (service.getByNameAndOwner(name,owner).isEmpty()) {
                        service.insert(
                            RepositoryDataBase(
                                0,
                                name,
                                owner
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onClickDownload(view: View, repository: Repository) {
        viewModel.download(repository)

    }




    override fun onClickShare(view: View, repository: Repository) {
        val callIntent: Intent = Uri.parse("https://github.com/${repository.owner.login}/${repository.name}").let { uri ->
            Intent(Intent.ACTION_VIEW, uri)
        }
        startActivity(callIntent)
    }

}


