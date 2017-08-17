package com.lp.practice.neihantv.mvp.presenter

import android.content.Context
import com.lp.practice.neihantv.mvp.contract.HomeContract
import com.lp.practice.neihantv.mvp.model.HomeModel
import com.lp.practice.neihantv.mvp.model.bean.HomeBean
import com.lp.practice.neihantv.utils.applySchedulers

/**
 * Created by LiPin on 2017/8/17 15:52.
 * 描述：
 */
class HomePresenter(context: Context, view: HomeContract.View) : HomeContract.Presenter {

    var mContext: Context? = null
    var mView: HomeContract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedulers()?.subscribe { bean: HomeBean -> mView?.setData(bean) }
    }

    fun moreData(data: String?) {
        val observable = mContext?.let { mModel.loadData(it, false, data) }
        observable?.applySchedulers()?.subscribe { bean: HomeBean -> mView?.setData(bean) }
    }
}