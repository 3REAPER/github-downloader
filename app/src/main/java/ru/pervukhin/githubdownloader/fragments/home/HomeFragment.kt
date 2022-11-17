package ru.pervukhin.githubdownloader.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.pervukhin.githubdownloader.MainActivity
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.adapter.RepositoryFindAdapter
import ru.pervukhin.githubdownloader.domain.Repository
import java.io.File


class HomeFragment : Fragment(), RepositoryFindAdapter.RepositoryClick {

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: RepositoryFindAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val name = view.findViewById<TextInputEditText>(R.id.name)
        val nameLayout = view.findViewById<TextInputLayout>(R.id.name_field)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_repository)
        adapter = RepositoryFindAdapter(this)
        recyclerView.adapter = adapter

        nameLayout.setEndIconOnClickListener{
            viewModel.search(name.text.toString())
        }

        viewModel.listRepositoryLiveData.observe(viewLifecycleOwner){ list ->
            if (list != null) {
                adapter.setList(list)
            }else {
                Toast.makeText(context,R.string.not_found,Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.zipLiveData.observe(viewLifecycleOwner){
            when(it){
                "Success" -> Toast.makeText(context,R.string.download_success,Toast.LENGTH_SHORT).show()
                "Error" -> Toast.makeText(context,R.string.download_error,Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun onClickDownload(view: View, repository: Repository) {
       viewModel.download(repository.owner.login, repository.name)
    }

    override fun onClickShare(view: View, repository: Repository) {
        val callIntent: Intent = Uri.parse("https://github.com/${repository.owner.login}/${repository.name}").let { uri ->
            Intent(Intent.ACTION_VIEW, uri)
        }
        startActivity(callIntent)
    }
}


