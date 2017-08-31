package com.lp.practice.neihantv.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by LiPin on 2017/8/31 15:05.
 * 描述：
 */
class HotAdatpter(fm: FragmentManager, mFragments: ArrayList<Fragment>, mTabs: MutableList<String>) : FragmentPagerAdapter(fm) {

    var mFm: FragmentManager = fm!!
    var mList: ArrayList<Fragment> = mFragments
    var mTitles: MutableList<String> = mTabs
    override fun getItem(position: Int): Fragment {
        return mList[position]

    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}