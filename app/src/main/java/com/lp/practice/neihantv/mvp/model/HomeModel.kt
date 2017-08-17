package com.lp.practice.neihantv.mvp.model

import android.content.Context
import com.lp.practice.neihantv.mvp.model.bean.HomeBean
import com.lp.practice.neihantv.network.ApiService
import com.lp.practice.neihantv.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/17 14:32.
 * 描述：
 */
class HomeModel {
    fun loadData(context: Context, isFirst: Boolean, data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        when (isFirst) {
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}