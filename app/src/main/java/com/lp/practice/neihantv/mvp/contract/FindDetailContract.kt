package com.lp.practice.neihantv.mvp.contract

import com.lp.practice.neihantv.base.BasePresenter
import com.lp.practice.neihantv.base.BaseView
import com.lp.practice.neihantv.mvp.model.bean.HotBean

/**
 * Created by LiPin on 2017/8/30 13:21.
 * 描述：
 */
interface FindDetailContract {

    interface View : BaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(categoryName: String, strategy: String)
    }
}