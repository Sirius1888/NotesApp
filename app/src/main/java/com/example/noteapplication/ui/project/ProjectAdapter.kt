package com.example.noteapplication.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.ColorType.getProjectColorType
import com.example.noteapplication.base.BaseAdapter
import com.example.noteapplication.base.BaseViewHolder
import com.example.noteapplication.data.model.Project
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectAdapter(private var listener: ClickListener) : BaseAdapter() {

    private var items = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ProjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as ProjectViewHolder
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

class ProjectViewHolder(itemView: View) : BaseViewHolder(itemView) {
    fun bind(item: Project) {
        itemView.view_project_indicator.setBackgroundColor(getProjectColorType(item.color))
        itemView.tv_title.text = item.name
    }
}