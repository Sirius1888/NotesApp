package com.example.noteapplication.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.ColorType.getProjectColorType
import com.example.noteapplication.data.model.Project
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(private var listener: ClickListener) : RecyclerView.Adapter<ProjectViewHolder>() {

    private var items = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    fun addItems(data: MutableList<Project>) {
        items = data
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: Project)
    }

}

class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Project) {
        itemView.view_project_indicator.setBackgroundColor(getProjectColorType(item.color))
        itemView.tv_title.text = item.name

    }

}