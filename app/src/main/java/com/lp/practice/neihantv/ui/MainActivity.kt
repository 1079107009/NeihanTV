package com.lp.practice.neihantv.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.search.SEARCH_TAG
import com.lp.practice.neihantv.search.SearchFragment
import com.lp.practice.neihantv.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var homeFragment: HomeFragment? = null
    lateinit var searchFragment: SearchFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val window = window
        window.attributes.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        //Activity异常关闭重新打开时
        if (savedInstanceState != null) {
            val fragments = supportFragmentManager.fragments
            for (item in fragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_content, homeFragment)
            transaction.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment).commit()
    }

    private fun initToolbar() {
        var today = getToday()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            searchFragment = SearchFragment()
            searchFragment.show(fragmentManager, SEARCH_TAG)
        }
    }

    private fun getToday(): String {
        val list = arrayOf("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val date: Date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) {
            index = 0
        }
        return list[index]
    }

    private fun setRadioButton() {

    }

    override fun onBackPressed() {
        moveTaskToBack(false)
    }
}
