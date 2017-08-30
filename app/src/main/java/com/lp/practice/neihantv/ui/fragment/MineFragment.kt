package com.lp.practice.neihantv.ui.fragment

import android.graphics.Typeface
import android.view.View
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.ui.AdviseActivity
import com.lp.practice.neihantv.ui.CacheActivity
import com.lp.practice.neihantv.ui.WatchActivity
import com.lp.practice.neihantv.utils.newIntent

import kotlinx.android.synthetic.main.mine_fragment.*

/**
 * Created by LiPin on 2017/8/30 14:15.
 * 描述：
 */
class MineFragment : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_watch -> {
                activity.newIntent <WatchActivity>()
            }
            R.id.tv_advise -> {
                activity.newIntent<AdviseActivity>()
            }
            R.id.tv_save -> {
                activity.newIntent<CacheActivity>()
            }
        }
    }

    override fun initView() {
        tv_advise.setOnClickListener(this)
        tv_watch.setOnClickListener(this)
        tv_save.setOnClickListener(this)
        tv_advise.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_watch.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_save.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    override fun getLayoutResources(): Int {
        return R.layout.mine_fragment
    }
}