package com.lp.practice.neihantv.network

import com.lp.practice.neihantv.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by LiPin on 2017/8/17 14:39.
 * 描述：
 */
interface ApiService {

    companion object {
        val BASE_URL: String
            get() = "http://baobab.kaiyanapp.com/api/"
    }

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date: String, @Query("num") num: String): Observable<HomeBean>
}