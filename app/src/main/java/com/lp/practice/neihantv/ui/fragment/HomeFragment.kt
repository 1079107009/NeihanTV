package com.lp.practice.neihantv.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.adapter.HomeAdapter
import com.lp.practice.neihantv.mvp.contract.HomeContract
import com.lp.practice.neihantv.mvp.model.bean.HomeBean
import com.lp.practice.neihantv.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.regex.Pattern

/**
 * Created by LiPin on 2017/8/17 14:26.
 * 描述：
 */
class HomeFragment : BaseFragment(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    var mIsFresh: Boolean = false
    var mPresenter: HomePresenter? = null
    var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
    var mAdapter: HomeAdapter? = null
    var data: String? = null

    override fun onRefresh() {
        if (!mIsFresh) {
            mIsFresh = true
            mPresenter?.start()
        }
    }

    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val compile = Pattern.compile(regEx)
        val matcher = compile.matcher(bean.nextPageUrl)
        data = matcher.replaceAll("").subSequence(1, matcher.replaceAll("").length - 1).toString()
        if (mIsFresh) {
            mIsFresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }
        }
        bean.issueList!!.flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        mPresenter = HomePresenter(context, this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdapter(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val position = linearLayoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && position == mList.size - 1) {
                    if (data != null) {
                        mPresenter?.moreData(data)
                    }
                }
            }
        })

    }

    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

}

