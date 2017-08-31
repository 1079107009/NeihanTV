package com.lp.practice.neihantv.mvp.model

import android.content.Context
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.network.ApiService
import com.lp.practice.neihantv.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/31 15:12.
 * 描述：
 */
class HotModel {
    fun loadData(context: Context, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getHotData(10, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }
}