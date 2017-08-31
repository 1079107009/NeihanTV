package com.lp.practice.neihantv.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.adapter.RankAdapter
import com.lp.practice.neihantv.mvp.contract.HotContract
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.mvp.presenter.HotPresenter
import kotlinx.android.synthetic.main.rank_fragment.*

/**
 * Created by LiPin on 2017/8/31 15:04.
 * 描述：
 */
class RankFragment : BaseFragment(), HotContract.View {
    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context, mList)
        recyclerView.adapter = mAdapter
        if (arguments != null) {
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }

    }

    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if (mList.size > 0) {
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }
}