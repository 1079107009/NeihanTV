package com.lp.practice.neihantv.mvp.presenter

import android.content.Context
import com.lp.practice.neihantv.mvp.contract.FindDetailContract
import com.lp.practice.neihantv.mvp.model.FindDetailModel
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/30 13:39.
 * 描述：
 */
class FindDetailPresenter(context: Context, view: FindDetailContract.View) : FindDetailContract.Presenter {

    var mContext: Context? = null
    var mView: FindDetailContract.View? = null
    val mModel: FindDetailModel by lazy {
        FindDetailModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {

    }

    override fun requestData(categoryName: String, strategy: String) {
        mModel.loadData(mContext!!, categoryName, strategy)
                ?.applySchedulers()
                ?.subscribe {
                    bean: HotBean ->
                    mView?.setData(bean)
                }
    }

    fun requestMoreData(start: Int, categoryName: String, strategy: String) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadMoreData(mContext!!, start, categoryName, strategy) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}