package com.lp.practice.neihantv.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.adapter.HotAdatpter
import kotlinx.android.synthetic.main.hot_fragment.*

/**
 * Created by LiPin on 2017/8/30 14:15.
 * 描述：
 */
class HotFragment : BaseFragment() {
    var mTabs = listOf("周排行", "月排行", "总排行").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly", "monthly", "historical")
    override fun getLayoutResources(): Int {
        return R.layout.hot_fragment
    }

    override fun initView() {
        var weekFragment: RankFragment = RankFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy", STRATEGY[0])
        weekFragment.arguments = weekBundle
        var monthFragment: RankFragment = RankFragment()
        var monthBundle = Bundle()
        monthBundle.putString("strategy", STRATEGY[1])
        monthFragment.arguments = monthBundle
        var allFragment: RankFragment = RankFragment()
        var allBundle = Bundle()
        allBundle.putString("strategy", STRATEGY[2])
        allFragment.arguments = allBundle
        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(monthFragment as Fragment)
        mFragments.add(allFragment as Fragment)
        vp_content.adapter = HotAdatpter(fragmentManager, mFragments, mTabs)
        tabs.setupWithViewPager(vp_content)
    }
}