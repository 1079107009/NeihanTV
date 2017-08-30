package com.lp.practice.neihantv.mvp.model

import android.content.Context
import com.lp.practice.neihantv.mvp.model.bean.HotBean
import com.lp.practice.neihantv.network.ApiService
import com.lp.practice.neihantv.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by LiPin on 2017/8/30 13:24.
 * 描述：
 */
class FindDetailModel {

    fun loadData(context: Context, categoryName: String, strategy: String?): Observable<HotBean>? {
        val apiService = RetrofitClient.getInstance(context, ApiService.BASE_URL)
                .create(ApiService::class.java)
        return apiService?.getFindDetailData(categoryName, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }

    fun loadMoreData(context: Context, start: Int, categoryName: String, strategy: String?): Observable<HotBean>? {
        val apiService = RetrofitClient.getInstance(context, ApiService.BASE_URL)
                .create(ApiService::class.java)
        return apiService?.getFindDetailMoreData(start, 10, categoryName, strategy!!)
    }
}