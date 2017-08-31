package com.lp.practice.neihantv.mvp.presenter

import android.content.Context
import com.lp.practice.neihantv.mvp.contract.ResultContract
import com.lp.practice.neihantv.mvp.model.ResultModel
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/7/7 15:14.
 * 描述：
 */
class ResultPresenter(context: Context, view: ResultContract.View) : ResultContract.Presenter {


    var mContext: Context? = null
    var mView: ResultContract.View? = null
    val mModel: ResultModel by lazy {
        ResultModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestData(query: String, start: Int) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, query, start) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}