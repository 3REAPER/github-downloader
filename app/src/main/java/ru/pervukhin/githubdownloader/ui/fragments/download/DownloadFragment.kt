package ru.pervukhin.githubdownloader.ui.fragments.download

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.ui.adapter.DownloadAdapter
import ru.pervukhin.githubdownloader.data.room.AppDataBase

class DownloadFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(DownloadViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_download, container, false)
        viewModel.roomDataBase = context?.let { AppDataBase.getDatabase(it) }!!
        val adapter = DownloadAdapter()
        val downloadRecycler = view.findViewById<RecyclerView>(R.id.rv_download)
        downloadRecycler.adapter = adapter

        viewModel.getAll()

        viewModel.repositoryLiveData.observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
            }
        }
        return view
    }
}