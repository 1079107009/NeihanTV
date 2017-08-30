package com.lp.practice.neihantv.ui.fragment

import android.content.Intent
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.adapter.FindAdapter
import com.lp.practice.neihantv.mvp.contract.FindContract
import com.lp.practice.neihantv.mvp.model.bean.FindBean
import com.lp.practice.neihantv.mvp.presenter.FindPresenter
import com.lp.practice.neihantv.ui.FindDetailActivity
import kotlinx.android.synthetic.main.find_fragment.*

/**
 * Created by LiPin on 2017/8/30 10:17.
 * 描述：发现
 */
class FindFragment : BaseFragment(), FindContract.View {

    var mPresenter: FindPresenter? = null
    var mAdapter: FindAdapter? = null
    var mList: MutableList<FindBean>? = null

    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mList = beans
        mAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        mPresenter = FindPresenter(context, this)
        mPresenter?.start()
        mAdapter = FindAdapter(context, mList)
        gv_find.adapter = mAdapter
        gv_find.setOnItemClickListener { parent, view, position, id ->
            val bean = mList?.get(position)
            val name = bean?.name
            val intent: Intent = Intent(context, FindDetailActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)

        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }
}