package com.lp.practice.neihantv.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.mvp.model.bean.HomeBean

/**
 * Created by LiPin on 2017/8/24 20:07.
 * 描述：
 */
class HomeAdapter(context: Context, list: MutableList<HomeBean.IssueListBean.ItemListBean>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var mContext: Context? = null
    var mList: MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var inflate: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        inflate = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeAdapter.HomeViewHolder {
        val view = inflate?.inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view!!, mContext!!)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class HomeViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_detail: TextView? = null
        var tv_title: TextView? = null
        var tv_time: TextView? = null
        var iv_photo: ImageView? = null
        var iv_user: ImageView? = null

        init {
            tv_detail = itemView?.findViewById(R.id.tv_detail) as TextView?
            tv_title = itemView?.findViewById(R.id.tv_title) as TextView?
            iv_photo = itemView?.findViewById(R.id.iv_photo) as ImageView?
            iv_user = itemView?.findViewById(R.id.iv_user) as ImageView?
            tv_title?.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")

        }
    }
}