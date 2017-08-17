package com.lp.practice.neihantv.utils

import android.app.Activity
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by LiPin on 2017/8/17 13:49.
 * 描述：
 */
inline fun <reified T : Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}