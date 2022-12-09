package com.practice.likemindsassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practice.likemindsassignment.R
import com.practice.likemindsassignment.model.Definition

class ResultAdapter(private val resultList: ArrayList<Definition>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var wordTxt: TextView = itemView.findViewById<TextView>(R.id.mean_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordTxt.text = resultList[position].definition
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}