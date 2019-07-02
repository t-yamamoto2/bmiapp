package com.example.bmiapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.List
import android.support.v4.view.ViewPager
import android.util.Log


class ViewAdapter(private val list: List<PersonalDataModel>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateView.text = list.get(position).date
        holder.heightlView.text = list.get(position).height
        holder.weightlView.text = list.get(position).weight
        holder.bmilView.text = list.get(position).bmi
        holder.commentView.text = list.get(position).comment
    }

    override fun getItemCount(): Int {
        return list.size
    }
}