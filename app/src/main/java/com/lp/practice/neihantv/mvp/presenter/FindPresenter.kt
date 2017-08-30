package com.lp.practice.neihantv.mvp.presenter

import android.content.Context
import com.lp.practice.neihantv.mvp.contract.FindContract
import com.lp.practice.neihantv.mvp.model.FindModel
import com.lp.practice.neihantv.mvp.model.bean.FindBean
import com.lp.practice.neihantv.utils.applySchedulers

/**
 * Created by LiPin on 2017/8/17 15:52.
 * 描述：
 */
class FindPresenter(context: Context, view: FindContract.View) : FindContract.Presenter {

    var mContext: Context? = null
    var mView: FindContract.View? = null
    val mModel: FindModel by lazy {
        FindModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        mModel.loadData(mContext!!).applySchedulers().subscribe {
            beans: MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }

}