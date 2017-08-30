package com.lp.practice.neihantv.mvp.model

import android.content.Context
import com.lp.practice.neihantv.mvp.model.bean.FindBean
import com.lp.practice.neihantv.network.ApiService
import com.lp.practice.neihantv.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/30 10:29.
 * 描述：
 */
class FindModel {
    fun loadData(context: Context): Observable<MutableList<FindBean>> {
        val apiService = RetrofitClient.getInstance(context, ApiService.BASE_URL)
                .create(ApiService::class.java)
        return apiService!!.getFindData()
    }
}