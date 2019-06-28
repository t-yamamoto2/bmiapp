package com.example.bmiapp


class PersonalDataModel(today: String, heightCmView: String, weightKgView: String, resultBmiView: String, commentView: String) {
    val height: String = heightCmView
    val weight: String = weightKgView
    val comment: String = commentView
    val bmi: String = resultBmiView
    val date: String = today
}
