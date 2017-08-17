package com.lp.practice.neihantv.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by LiPin on 2017/8/17 14:47.
 * 描述：
 */
object NetworkUtils {

    fun isNetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        return networkInfo.isAvailable && networkInfo.isConnected
    }

    fun isNetworkConnected(context: Context, type: Int): Boolean {
        if (!isNetConnected(context)) {
            return false
        }
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(type) ?: return false
        return networkInfo.isAvailable && networkInfo.isConnected
    }

    fun isPhoneNetConnected(context: Context): Boolean {
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetworkConnected(context, typeMobile)
    }

    fun isWifiNetConnected(context: Context): Boolean {
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return isNetworkConnected(context, typeMobile)
    }
}