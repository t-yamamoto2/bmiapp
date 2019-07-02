package com.example.bmiapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import java.text.SimpleDateFormat
import com.squareup.moshi.*
import java.util.*
import java.util.List

val moshi = Moshi.Builder().build()
val type = Types.newParameterizedType(List::class.java,PersonalDataModel::class.java)
val listAdapter:JsonAdapter<List<PersonalDataModel>> = moshi.adapter(type)



//計算タブ
class TabMainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //計算処理
        val resultBmiView: TextView = view.findViewById(R.id.result)
        val calcButton: Button = view.findViewById(R.id.calcButton)
        val heightCmView: EditText = view.findViewById(R.id.height)
        val weightKgView: EditText = view.findViewById(R.id.weight)
        resultBmiView.setText("")

        //計算ボタン
        calcButton.setOnClickListener {
            val heightCmStr = heightCmView.text.toString()
            val weightKgStr = weightKgView.text.toString()

            val heightCmDbl = toDoubleOrNegative(heightCmStr)
            val weightKgDbl = toDoubleOrNegative(weightKgStr)

            if (heightCmDbl <= 0 || weightKgDbl <= 0) {
                Toast.makeText(this.context, "身長と体重に正しい数値を入力してください", Toast.LENGTH_LONG).show()
            } else {
                val resultBmi = weightKgDbl / (heightCmDbl * heightCmDbl) * 10000
                val resultFormatBmi = String.format("%1$.1f", resultBmi)
                resultBmiView.setText(resultFormatBmi)
            }
        }

        //保存処理
        val commentView: EditText = view.findViewById(R.id.comment)
        val deleteBtn: Button = view.findViewById(R.id.deleteBtn)
        val saveBtn: Button = view.findViewById(R.id.saveBtn)

        //削除ボタン
        deleteBtn.setOnClickListener {
            heightCmView.setText("")
            weightKgView.setText("")
            resultBmiView.setText("")
            commentView.setText("")

        }
        //保存ボタン
        saveBtn.setOnClickListener {
            val dataStore: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
            val editor = dataStore.edit()
            val day = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Date())

            val heightCmDbl = toDoubleOrNegative(heightCmView.text.toString())
            val weightKgDbl = toDoubleOrNegative(weightKgView.text.toString())

            editor.apply {
                if (heightCmDbl <= 0 && weightKgDbl <= 0) {
                    Toast.makeText(context, "BMI計算結果がありません", Toast.LENGTH_LONG).show()
                } else {
                    val personalData = PersonalDataModel(day,heightCmView.text.toString(),weightKgView.text.toString(),resultBmiView.text.toString(),commentView.text.toString())
                    val savedData = dataStore.getString("History", "[]")
                    val savedDataList : List<PersonalDataModel>  = listAdapter.fromJson(savedData) as  List<PersonalDataModel>
                    //保存済みのjsonに計算結果オブジェクトを追加
                    savedDataList.add(personalData)
                    val json = listAdapter.toJson(savedDataList)
                    editor.putString("History",json)
                    Toast.makeText(context, "保存しました", Toast.LENGTH_LONG).show()
                }
                editor.apply()
            }
        }
    }
    private fun toDoubleOrNegative(text: String): Double {
        var result: Double
        try {
            result = text.toDouble()
        } catch (e: NumberFormatException) {
            result = -1.0
        }
        return result
    }

}



