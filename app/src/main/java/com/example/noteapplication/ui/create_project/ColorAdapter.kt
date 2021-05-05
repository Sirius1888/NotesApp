package com.example.noteapplication.ui.create_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseAdapter
import com.example.noteapplication.base.BaseViewHolder
import com.example.noteapplication.data.model.PrimaryColor

class ColorAdapter(private var listener: ClickListener) : BaseAdapter() {

    private var items = mutableListOf<PrimaryColor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ColorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as ColorViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    fun addItems(data: MutableList<PrimaryColor>) {
        items = data
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: PrimaryColor)
    }
}

class ColorViewHolder(itemView: View) : BaseViewHolder(itemView) {
    fun bind(item: PrimaryColor) {

    }
}