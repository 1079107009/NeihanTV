package com.lp.practice.neihantv.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by LiPin on 2017/8/17 14:05.
 * 描述：
 */
abstract class BaseFragment : Fragment() {
    var isFirst: Boolean = false
    var rootView: View? = null
    var isFragmentVisible: Boolean = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isFragmentVisible = true
        }
        if (rootView == null) {
            return
        }
        //可见并且没有加载过
        if (!isFirst && isFragmentVisible) {
            onFragmentVisibleChange(true)
            return
        }

        //可见-->不可见，已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false)
            isFragmentVisible = false
        }
    }

    /**
     * true表示可见，false表示不可见
     */
    open protected fun onFragmentVisibleChange(b: Boolean) {

    }

    abstract fun initView()

    abstract fun getLayoutResources(): Int
}