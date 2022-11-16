package ru.pervukhin.githubdownloader.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.ls.LSException
import ru.pervukhin.githubdownloader.R
import ru.pervukhin.githubdownloader.domain.Repository

class RepositoryFindAdapter : RecyclerView.Adapter<RepositoryFindAdapter.RepositoryFindViewHolder>() {
    private var list: List<Repository> = listOf()

    fun setList(list: List<Repository>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryFindViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryFindViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryFindViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.name)
        val language = holder.itemView.findViewById<TextView>(R.id.language)
        val createdAt = holder.itemView.findViewById<TextView>(R.id.created_at)
        val pushedAt = holder.itemView.findViewById<TextView>(R.id.pushed_at)

        val repository = list[position]
        name.text = repository.name
        language.setTextColor(setColor(repository.language))
        language.text = repository.language
        createdAt.text = "create at " + repository.createdAt.split("T")[0]
        pushedAt.text = "pushed at " + repository.pushedAt.split("T")[0]
    }

    private fun setColor(language: String?): Int {
        when(language){
            "Java" -> return Color.parseColor("#e35720")
            "Kotlin" -> return Color.parseColor("#ec47f5")
            "C#" -> return Color.parseColor("#6250eb")
            "C++" -> return Color.parseColor("#321aeb")
            "Html" -> return Color.parseColor("#ab2626")
            "CSS" -> return Color.parseColor("#30c5f2")
            "Python" -> return Color.parseColor("#7fed1f")
        }
        return Color.parseColor("#000000")
    }


    override fun getItemCount(): Int {
       return list.size
    }




    class RepositoryFindViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }


}