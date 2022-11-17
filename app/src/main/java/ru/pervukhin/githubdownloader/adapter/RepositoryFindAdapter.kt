package ru.pervukhin.githubdownloader.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.ls.LSException
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.domain.Repository

class RepositoryFindAdapter(private val repositoryClick: RepositoryClick) : RecyclerView.Adapter<RepositoryFindAdapter.RepositoryFindViewHolder>() {
    private var list: List<Repository> = listOf()

    fun setList(list: List<Repository>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryFindViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository_cardview, parent, false)
        return RepositoryFindViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryFindViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val description = holder.itemView.findViewById<TextView>(R.id.description)
        val language = holder.itemView.findViewById<TextView>(R.id.language)
        val star = holder.itemView.findViewById<TextView>(R.id.star)
        val watchers = holder.itemView.findViewById<TextView>(R.id.watchers)
        val forks = holder.itemView.findViewById<TextView>(R.id.forks)
        val download = holder.itemView.findViewById<MaterialButton>(R.id.download)
        val share = holder.itemView.findViewById<MaterialButton>(R.id.share)


        val repository = list[position]
        name.text = repository.name
        description.text = repository.description
        if (repository.language == null){
               repository.language = "Markdown"
        }
        language.text = repository.language
        star.text = repository.starCount.toString()
        watchers.text = repository.watchersCount.toString()
        forks.text = repository.forksCount.toString()

        download.setOnClickListener {
            repositoryClick.onClickDownload(it, repository)
        }

        share.setOnClickListener {
            repositoryClick.onClickShare(it, repository)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class RepositoryFindViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    interface RepositoryClick{
        fun onClickDownload(view: View, repository: Repository)

        fun onClickShare(view: View, repository: Repository)
    }


}