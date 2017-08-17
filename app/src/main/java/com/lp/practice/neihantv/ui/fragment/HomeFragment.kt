package com.lp.practice.neihantv.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.mvp.contract.HomeContract
import com.lp.practice.neihantv.mvp.model.bean.HomeBean

/**
 * Created by LiPin on 2017/8/17 14:26.
 * 描述：
 */
class HomeFragment : BaseFragment(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {


    override fun onRefresh() {

    }

    override fun setData(bean: HomeBean) {

    }

    override fun initView() {

    }

    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

}