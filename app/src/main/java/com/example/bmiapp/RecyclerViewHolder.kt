package com.example.bmiapp
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView


class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
        val item1 : TextView = view.findViewById(R.id.item1)
        val item2 : TextView = view.findViewById(R.id.item2)

    init {
        // layoutの初期設定するときはココ
    }


}