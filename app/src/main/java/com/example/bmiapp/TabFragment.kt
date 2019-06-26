package com.example.bmiapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import java.util.*

class TabMainFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {







        return inflater.inflate(R.layout.fragment_main,container,false)
    }
}

class TabHistryFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val historyList = getHistoryList()
        val recyclerView = R.id.recycler_view

        return inflater.inflate(R.layout.fragment_history, container, false)
    }




    private fun getHistoryList(): List<History> {
        //試しに履歴を作成してArrayListにえいや
        val date = Date()
        val HistoryList = ArrayList<History>()
        HistoryList.add(History(1,100,10,"言い訳1",  1, date))
        HistoryList.add(History(2,200,20,"言い訳2",  2, date))
        HistoryList.add(History(3,300,30,"言い訳3",  3, date))
        return HistoryList
    }
}
