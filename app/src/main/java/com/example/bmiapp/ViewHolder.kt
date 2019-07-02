package com.example.bmiapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dateView: TextView = itemView.findViewById(R.id.row_date)
        val heightlView: TextView = itemView.findViewById(R.id.row_height)
        val weightlView: TextView = itemView.findViewById(R.id.row_weight)
        val bmilView: TextView = itemView.findViewById(R.id.row_bmi)
        val commentView: TextView = itemView.findViewById(R.id.row_comment)
}
