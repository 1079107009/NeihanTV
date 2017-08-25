package com.lp.practice.neihantv.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.mvp.model.bean.HomeBean
import com.lp.practice.neihantv.mvp.model.bean.VideoBean
import com.lp.practice.neihantv.ui.VideoDetailActivity
import com.lp.practice.neihantv.utils.ImageLoadUtils
import com.lp.practice.neihantv.utils.ObjectSaveUtils
import com.lp.practice.neihantv.utils.SPUtils

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
        val bean = mList?.get(position)
        val title = bean?.data?.title
        val category = bean?.data?.category
        val minute = bean?.data?.duration?.div(60)
        val second = bean?.data?.duration?.minus((minute?.times(60)) as Long)
        val realMinute: String
        val realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        val photo = bean.data?.cover?.feed
        val author = bean.data?.author
        ImageLoadUtils.display(mContext!!, holder?.iv_photo, photo as String)
        holder?.tv_title?.text = title
        holder?.tv_detail?.text = "发布于 $category / $realMinute:$realSecond"
        if (author != null) {
            ImageLoadUtils.display(mContext!!, holder?.iv_user, author.icon as String)
        } else {
            holder?.iv_user?.visibility = View.GONE
        }
        holder?.itemView?.setOnClickListener {
            //跳转视频详情页
            val intent: Intent = Intent(mContext, VideoDetailActivity::class.java)
            val desc = bean.data?.description
            val duration = bean.data?.duration
            val playUrl = bean.data?.playUrl
            val blurred = bean.data?.cover?.blurred
            val collect = bean.data?.consumption?.collectionCount
            val share = bean.data?.consumption?.shareCount
            val reply = bean.data?.consumption?.replyCount
            val time = System.currentTimeMillis()
            val videoBean = VideoBean(photo, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
            val url = SPUtils.getInstance(mContext!!, "beans").getString(playUrl!!)
            if (url.equals("")) {
                var count = SPUtils.getInstance(mContext!!, "beans").getInt("count")
                if (count != -1) {
                    count = count.inc()
                } else {
                    count = 1
                }
                SPUtils.getInstance(mContext!!, "beans").put("count", count)
                SPUtils.getInstance(mContext!!, "beans").put(playUrl, playUrl)
                ObjectSaveUtils.saveObject(mContext!!, "bean$count", videoBean)
            }
            intent.putExtra("data", videoBean as Parcelable)
            mContext?.startActivity(intent)
        }
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
            tv_detail = itemView.findViewById(R.id.tv_detail) as TextView?
            tv_title = itemView.findViewById(R.id.tv_title) as TextView?
            iv_photo = itemView.findViewById(R.id.iv_photo) as ImageView?
            iv_user = itemView.findViewById(R.id.iv_user) as ImageView?
            tv_title?.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")

        }
    }
}