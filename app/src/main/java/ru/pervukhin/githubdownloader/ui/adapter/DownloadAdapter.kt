package ru.pervukhin.githubdownloader.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.data.room.RepositoryDataBase

class DownloadAdapter: RecyclerView.Adapter<DownloadAdapter.DownloadHolder>() {
    private var list: List<RepositoryDataBase> = listOf()

    fun setList(list: List<RepositoryDataBase>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)
        return DownloadHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val owner = holder.itemView.findViewById<TextView>(R.id.owner)


        val repository = list[position]
        name.text = repository.name
        owner.text = repository.owner
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class DownloadHolder(view: View) : RecyclerView.ViewHolder(view){

    }
}


