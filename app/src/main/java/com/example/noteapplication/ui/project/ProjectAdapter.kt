package com.example.noteapplication.ui.project

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.ColorType.getProjectColorType
import com.example.noteapplication.base.BaseAdapter
import com.example.noteapplication.base.BaseViewHolder
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.databinding.ItemColorBinding
import com.example.noteapplication.databinding.ItemProjectBinding
import com.example.noteapplication.ui.create_project.ColorViewHolder

class ProjectAdapter(private var listener: ClickListener) : BaseAdapter() {

    private var items = mutableListOf<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
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

class ProjectViewHolder(var binding: ItemProjectBinding) : BaseViewHolder(binding.root) {
    fun bind(item: Project) {
        binding.viewProjectIndicator.setBackgroundColor(getProjectColorType(item.color))
        binding.tvTitle.text = item.name
    }
}