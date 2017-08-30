package com.lp.practice.neihantv.ui

import android.app.Activity
import android.os.Bundle
import com.lp.practice.neihantv.R
import kotlinx.android.synthetic.main.activity_advise.*

/**
 * Created by LiPin on 2017/8/30 9:53.
 * 描述：
 */

class AdviseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advise)
        tv_bar_title.text = "看把你能的，还敢提意见"
    }
}
