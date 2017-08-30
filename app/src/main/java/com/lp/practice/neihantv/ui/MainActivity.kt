package com.lp.practice.neihantv.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import com.gyf.barlibrary.ImmersionBar
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.search.SEARCH_TAG
import com.lp.practice.neihantv.search.SearchFragment
import com.lp.practice.neihantv.ui.fragment.FindFragment
import com.lp.practice.neihantv.ui.fragment.HomeFragment
import com.lp.practice.neihantv.ui.fragment.HotFragment
import com.lp.practice.neihantv.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragment: HotFragment? = null
    var mineFragment: MineFragment? = null
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
                if (item is FindFragment) {
                    findFragment = item
                }
                if (item is HotFragment) {
                    hotFragment = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            hotFragment = HotFragment()
            mineFragment = MineFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fl_content, homeFragment)
            transaction.add(R.id.fl_content, findFragment)
            transaction.add(R.id.fl_content, hotFragment)
            transaction.add(R.id.fl_content, mineFragment)
            transaction.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(findFragment)
                .hide(hotFragment)
                .hide(mineFragment)
                .commit()
    }

    private fun initToolbar() {
        val today = getToday()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_mine.isChecked) {

            } else {
                searchFragment = SearchFragment()
                searchFragment.show(fragmentManager, SEARCH_TAG)
            }
        }
    }

    private fun getToday(): String {
        val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
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
        rg_root.check(R.id.rb_home)
        rg_root.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_find -> {
                supportFragmentManager.beginTransaction().show(findFragment)
                        .hide(homeFragment)
                        .hide(mineFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_home -> {
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_hot -> {
                supportFragmentManager.beginTransaction().show(hotFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .commit()
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_mine -> {
                supportFragmentManager.beginTransaction().show(mineFragment)
                        .hide(findFragment)
                        .hide(homeFragment)
                        .hide(hotFragment)
                        .commit()
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.drawable.icon_setting)
            }
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
    }
}
