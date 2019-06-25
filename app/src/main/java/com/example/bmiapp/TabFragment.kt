package com.example.bmiapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.*
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
        HistoryList.add(History("言い訳1", 10, 1, date))
        HistoryList.add(History("言い訳2", 20, 2, date))
        return HistoryList
    }
}