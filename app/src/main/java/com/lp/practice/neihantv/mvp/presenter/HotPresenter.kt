package com.lp.practice.neihantv.mvp.presenter

import android.content.Context
import com.lp.practice.neihantv.mvp.contract.HotContract
import com.lp.practice.neihantv.mvp.model.HotModel
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/31 15:13.
 * 描述：
 */
class HotPresenter(context: Context, view: HotContract.View) : HotContract.Presenter {


    var mContext: Context? = null
    var mView: HotContract.View? = null
    val mModel: HotModel by lazy {
        HotModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestData(strategy: String) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, strategy) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}