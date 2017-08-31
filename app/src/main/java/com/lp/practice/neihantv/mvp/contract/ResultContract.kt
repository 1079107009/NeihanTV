package com.lp.practice.neihantv.mvp.contract

import com.lp.practice.neihantv.base.BasePresenter
import com.lp.practice.neihantv.base.BaseView
import com.lp.practice.neihantv.mvp.model.bean.HotBean

/**
 * Created by LiPin on 2017/7/11 15:15.
 * 描述：
 */
interface ResultContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(query: String, start: Int)
    }
}