package com.example.noteapplication.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.data.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private var listener: ClickListener) : RecyclerView.Adapter<TasksViewHolder>() {

    private var items = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
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
        holder.itemView.cb_task.setOnClickListener {
            listener.onCheckedClick(item)
        }
        holder.itemView.setOnLongClickListener {
            listener.onRemoveItemClick(item, position)
            true
        }
    }

    fun addItems(data: MutableList<Task>) {
        items = data
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: Task)
        fun onCheckedClick(item: Task)
        fun onRemoveItemClick(item: Task, position: Int)
    }

}

class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Task) {
        itemView.tv_task.text = item.content
        val stateOfTask = item.completed ?: false
        itemView.cb_task.isChecked = stateOfTask
    }
}
