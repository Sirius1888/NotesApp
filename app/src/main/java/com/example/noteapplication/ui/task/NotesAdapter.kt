package com.example.noteapplication.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.databinding.ItemNoteBinding
import com.example.noteapplication.databinding.ItemProjectBinding
import com.example.noteapplication.ui.project.ProjectViewHolder

class TaskAdapter(private var listener: ClickListener) : RecyclerView.Adapter<TasksViewHolder>() {

    private var items = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
        holder.binding.cbTask.setOnClickListener {
            listener.onCheckedClick(item, position)
        }
        holder.itemView.setOnLongClickListener {
            listener.onRemoveItemClick(item, position)
            true
        }
    }

    fun addItems(data: MutableList<Task>?) {
        data?.let {
            items = it
            notifyDataSetChanged()
        }
    }

    fun refreshItems(position: Int) {

    }

    interface ClickListener {
        fun onItemClick(item: Task)
        fun onCheckedClick(item: Task, position: Int)
        fun onRemoveItemClick(item: Task, position: Int)
    }

}

class TasksViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Task) {
        binding.tvTask.text = item.content
        val stateOfTask = item.completed ?: false
        binding.cbTask.isChecked = stateOfTask
    }
}
