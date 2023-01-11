package com.example.muchartssbu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.inflate


object comparateur: DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }

}

class Adapteur: ListAdapter<String, Adapteur.HolderView>(comparateur) {
    class HolderView(itemView: View): ViewHolder(itemView){

            val name: TextView = itemView.findViewById(R.id.Nom)

        fun bind(item: String){
            name.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_list, parent, false)
        return HolderView(view)
    }

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}