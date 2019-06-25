package com.example.bmiapp

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = TabAdapter(supportFragmentManager, this)
        tabLayout.setupWithViewPager(pager)
    }

}
