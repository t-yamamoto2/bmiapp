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
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*



class TabMainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        //計算処理
        val resultBmiView : TextView = view.findViewById(R.id.result)
        val calcButton : Button = view.findViewById(R.id.calcButton)
        val heightCmView : EditText = view.findViewById(R.id.height)
        val weightKgView : EditText = view.findViewById(R.id.weight)

        //計算ボタン
        calcButton.setOnClickListener {
            val heightCmStr   = heightCmView.text.toString()
            val weightKgStr   = weightKgView.text.toString()

            var heightCmDbl  = toDoubleOrNegative(heightCmStr)
            val weightKgDbl  = toDoubleOrNegative(weightKgStr)


            if(heightCmDbl <= 0 || weightKgDbl <= 0){
                Toast.makeText(this.context, "身長と体重に正しい数値を入力してください", Toast.LENGTH_LONG).show()
            }else {
                var resultBmi = weightKgDbl / (heightCmDbl * heightCmDbl) * 10000
                var resultFormatBmi = String.format("%1$.1f", resultBmi)
                resultBmiView.setText(resultFormatBmi)
            }
        }


        //保存処理
        val personalData = personalDataModel()
        val commentView :EditText = view.findViewById(R.id.comment)
        val deleteBtn : Button = view.findViewById(R.id.deleteBtn)
        val saveBtn : Button = view.findViewById(R.id.saveBtn)

        //削除ボタン
        deleteBtn.setOnClickListener {
            heightCmView.setText("")
            weightKgView.setText("")
            resultBmiView.setText("")
            commentView.setText("")

        }

        //保存ボタン
        saveBtn.setOnClickListener {

        }







        return view
    }
    private fun toDoubleOrNegative(text : String): Double {
        var result : Double
        try {
            result = text.toDouble()
        }catch (e :NumberFormatException){
            result = -1.0
        }
        return result
    }
}





class TabHistryFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }



}
