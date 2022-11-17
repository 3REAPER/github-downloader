package ru.pervukhin.githubdownloader.fragments.download

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.fragments.home.HomeViewModel

class DownloadFragment : Fragment() {

    companion object {
        fun newInstance() = DownloadFragment()
    }

    private lateinit var viewModel: DownloadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_download, container, false)

        return view
    }
}