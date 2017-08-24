package com.lp.practice.neihantv.mvp.contract

import com.lp.practice.neihantv.base.BasePresenter
import com.lp.practice.neihantv.base.BaseView
import com.lp.practice.neihantv.mvp.model.bean.HomeBean

/**
 * Created by LiPin on 2017/8/17 14:29.
 * 描述：
 */
interface HomeContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HomeBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}