package com.example.bmiapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.List

//履歴タブ
class TabHistryFragment: Fragment() {
    var adapter : ViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        return view
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val rv : RecyclerView? = view.findViewById(R.id.recycler_view)
         //itemごとの枠線
         val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
         val dataStore: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
         val savedData = dataStore.getString("History", "[]")
         val savedDataList : List<PersonalDataModel> = listAdapter.fromJson(savedData) as List<PersonalDataModel>

         adapter = ViewAdapter(savedDataList)
         val llm = LinearLayoutManager(this.context)

         rv?.addItemDecoration(itemDecoration)
         rv?.setHasFixedSize(true)
         rv?.setLayoutManager(llm)
         rv?.setAdapter(adapter)
    }
    /**
     *履歴タブを開いた時に、保存されている履歴を再取得してrecyclerViewに反映
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        val lastValue = userVisibleHint
        super.setUserVisibleHint(isVisibleToUser)
        if (!lastValue && isVisibleToUser) {
            val dataStore: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
            val savedData = dataStore.getString("History", "[]")
            val savedDataList : List<PersonalDataModel> = listAdapter.fromJson(savedData) as List<PersonalDataModel>
            adapter?.refresh(savedDataList)

        }
    }
}

