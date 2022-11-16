package ru.pervukhin.githubdownloader.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.adapter.RepositoryFindAdapter
import ru.pervukhin.githubdownloader.domain.Repository


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val name = view.findViewById<TextInputEditText>(R.id.name)
        val nameLayout = view.findViewById<TextInputLayout>(R.id.name_field)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_repository)
        val adapter = RepositoryFindAdapter()
        recyclerView.adapter = adapter

        nameLayout.setEndIconOnClickListener{
            viewModel.search(name.text.toString())
        }

        viewModel.liveData.observe(viewLifecycleOwner){ list ->
            list.body()?.let { adapter.setList(it) }
        }

        return view
    }

}