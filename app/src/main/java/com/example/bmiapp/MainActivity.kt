package com.example.bmiapp

import android.os.Bundle

import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (pager as ViewPager).adapter = TabAdapter(supportFragmentManager, this)
        tab_layout.setupWithViewPager(pager)
    }

}
