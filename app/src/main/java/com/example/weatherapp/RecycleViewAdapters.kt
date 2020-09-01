package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class MainAdapter(private val myDataset: MutableList<MutableList<String>>, private val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    class MainViewHolder(val recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val recyclerView = LayoutInflater.from(parent.context).inflate(R.layout.main_cell, parent, false) as RecyclerView
        return MainViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val secondaryViewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val secondaryViewAdapter = SecondaryAdapter(myDataset[position])
        holder.recyclerView.apply {
            layoutManager = secondaryViewManager
            adapter = secondaryViewAdapter
        }
    }

    override fun getItemCount() = myDataset.size
}

class SecondaryAdapter(private val subset: MutableList<String>) : RecyclerView.Adapter<SecondaryAdapter.SecondaryViewHolder>() {
    class SecondaryViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondaryViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.secondary_cell, parent, false) as TextView
        return SecondaryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: SecondaryViewHolder, position: Int) {
        holder.textView.text = subset[position]
    }

    override fun getItemCount() = subset.size
}