package com.lp.practice.neihantv.mvp.contract

import com.lp.practice.neihantv.base.BasePresenter
import com.lp.practice.neihantv.base.BaseView
import com.lp.practice.neihantv.mvp.model.bean.FindBean

/**
 * Created by LiPin on 2017/8/30 10:21.
 * 描述：
 */
interface FindContract {
    interface View : BaseView<Presenter> {
        fun setData(beans: MutableList<FindBean>)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}