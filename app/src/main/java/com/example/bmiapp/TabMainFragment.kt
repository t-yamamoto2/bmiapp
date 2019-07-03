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
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import java.util.List
import android.text.Editable
import android.text.TextWatcher



val moshi = Moshi.Builder().build()
val type = Types.newParameterizedType(List::class.java,PersonalDataModel::class.java)
val listAdapter:JsonAdapter<List<PersonalDataModel>> = moshi.adapter(type)



//計算タブ
class TabMainFragment: Fragment() {

    var changeFrag = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resultBmiView= result
        val calcButton = calcButton
        val heightCmView = height
        val weightKgView = weight
        val commentView = comment
        val deleteBtn = deleteBtn
        val saveBtn = saveBtn
        changeFrag = false


        heightCmView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                /*変更前*/
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                /*変更中*/
            }
            override fun afterTextChanged(s: Editable) {
                /*変更後*/
                Log.d("heightCmView","cahnged : ${heightCmView.text}")
                changeFrag = false
            }
        })
        weightKgView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                /*変更前*/
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                /*変更中*/
            }
            override fun afterTextChanged(s: Editable) {
                /*変更後*/
                Log.d("weightKgView","cahnged : ${weightKgView.text}")
                changeFrag = false
            }
        })

        /**
         * 計算ボタン
         * 入力した身長、体重からBMI値を算出して結果を画面に表示
         */
        calcButton.setOnClickListener {
            val heightCmStr = heightCmView.text.toString()
            val weightKgStr = weightKgView.text.toString()

            val heightCmDbl = toDoubleOrNegative(heightCmStr)
            val weightKgDbl = toDoubleOrNegative(weightKgStr)


            if (heightCmStr == "" || weightKgStr == "") {
                Toast.makeText(this.context, "身長と体重に数値を入力してください", Toast.LENGTH_LONG).show()
            }else if (heightCmDbl <= 0 || weightKgDbl <= 0) {
                Toast.makeText(this.context, "身長と体重に正しい数値を入力してください", Toast.LENGTH_LONG).show()
            } else {
                val resultBmi = weightKgDbl / (heightCmDbl * heightCmDbl) * 10000
                val resultFormatBmi = String.format("%1$.1f", resultBmi)
                resultBmiView.setText(resultFormatBmi)
                changeFrag = true
            }
        }

        /**
         * 削除ボタン
         * 画面上の入力欄、計算結果に空白を挿入
         */
        deleteBtn.setOnClickListener {
            heightCmView.setText("")
            weightKgView.setText("")
            resultBmiView.setText("")
            commentView.setText("")
            changeFrag = false

        }
        /**
         * 保存ボタン
         * SharedPreferencesに画面上の入力値、計算結果、保存実行時の時間をjson形式で保存
         */
        saveBtn.setOnClickListener {
            val dataStore: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
            val editor = dataStore.edit()
            val day = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Date())

            val heightCmDbl = toDoubleOrNegative(heightCmView.text.toString())
            val weightKgDbl = toDoubleOrNegative(weightKgView.text.toString())

            editor.apply {
                if (heightCmDbl <= 0 && weightKgDbl <= 0) {
                    Toast.makeText(context, "身長、体重を入力し計算を実行後に保存できます", Toast.LENGTH_LONG).show()
                } else if(!changeFrag) {
                    Toast.makeText(context, "計算を実行してください", Toast.LENGTH_LONG).show()
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

    /**
     * 入力値をDoubleに変換し返す
     * 文字列等の変換できない値の場合負数を返す
     */
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



