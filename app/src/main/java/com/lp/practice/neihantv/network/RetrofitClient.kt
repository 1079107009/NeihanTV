package com.lp.practice.neihantv.network

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by LiPin on 2017/8/17 15:14.
 * 描述：
 */
class RetrofitClient private constructor(context: Context, baseUrl: String) {

    var httpCacheDirectory: File? = null
    var cache: Cache? = null
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 20

    companion object {
        @Volatile
        var instance: RetrofitClient? = null

        fun getInstance(context: Context, baseUrl: String): RetrofitClient {
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance = RetrofitClient(context, baseUrl)
                    }
                }
            }
            return instance!!
        }
    }

    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(context.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }
        //创建OKHttp
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(CacheInterceptor(context))
                .addNetworkInterceptor(CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //创建retrofit
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    fun <T> create(service: Class<T>): T? {
        return retrofit?.create(service)
    }
}