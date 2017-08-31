package com.lp.practice.neihantv.mvp.model

import android.content.Context
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.network.ApiService
import com.lp.practice.neihantv.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/7/11 15:15.
 * 描述：
 */
class ResultModel {
    fun loadData(context: Context, query: String, start: Int): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getSearchData(10, query, start)

    }
}
