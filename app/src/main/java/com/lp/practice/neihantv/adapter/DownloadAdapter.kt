package com.lp.practice.neihantv.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.mvp.model.bean.VideoBean
import com.lp.practice.neihantv.ui.VideoDetailActivity
import com.lp.practice.neihantv.utils.ImageLoadUtils
import com.lp.practice.neihantv.utils.SPUtils
import io.reactivex.disposables.Disposable
import zlc.season.rxdownload2.RxDownload
import zlc.season.rxdownload2.entity.DownloadFlag

/**
 * Created by LiPin on 2017/9/1 8:41.
 * 描述：
 */
class DownloadAdapter(context: Context, list: ArrayList<VideoBean>) : RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {

    var context: Context? = null
    var list: ArrayList<VideoBean>? = null
    var inflater: LayoutInflater? = null
    var isDownload = false
    var hasLoaded = false
    lateinit var mOnLongListener: OnLongClickListener
    lateinit var disposable: Disposable

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DownloadViewHolder {
        return DownloadViewHolder(inflater?.inflate(R.layout.item_download, parent, false), context!!)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder?, position: Int) {
        val photoUrl: String? = list?.get(position)?.feed
        photoUrl?.let { ImageLoadUtils.display(context!!, holder?.iv_photo, it) }
        val title: String? = list?.get(position)?.title
        holder?.tv_title?.text = title
        val category = list?.get(position)?.category
        val duration = list?.get(position)?.duration
        isDownload = SPUtils.getInstance(context!!, "download_state").getBoolean(list?.get(position)?.playUrl!!)
        getDownloadState(list?.get(position)?.playUrl, holder)
        if (isDownload) {
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_stop)
        } else {
            holder?.iv_download_state?.setImageResource(R.drawable.icon_download_start)
        }
        holder?.iv_download_state?.setOnClickListener {
            if (isDownload) {
                isDownload = false
                SPUtils.getInstance(context!!, "download_state").put(list?.get(position)?.playUrl!!, false)
                holder.iv_download_state.setImageResource(R.drawable.icon_download_start)
                RxDownload.getInstance(context).pauseServiceDownload(list?.get(position)?.playUrl).subscribe()
            } else {
                isDownload = true
                SPUtils.getInstance(context!!, "download_state").put(list?.get(position)?.playUrl!!, true)
                holder.iv_download_state.setImageResource(R.drawable.icon_download_stop)
                addMission(list?.get(position)?.playUrl, position + 1)
            }
        }
        holder?.itemView?.setOnClickListener {
            //跳转视频详情页
            val intent: Intent = Intent(context, VideoDetailActivity::class.java)
            val desc = list?.get(position)?.description
            val playUrl = list?.get(position)?.playUrl
            val blurred = list?.get(position)?.blurred
            val collect = list?.get(position)?.collect
            val share = list?.get(position)?.share
            val reply = list?.get(position)?.reply
            val time = System.currentTimeMillis()
            val videoBean = VideoBean(photoUrl, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
            intent.putExtra("data", videoBean as Parcelable)
            if (hasLoaded) {
                val files = RxDownload.getInstance(context).getRealFiles(playUrl)
                val uri = Uri.fromFile(files!![0])
                intent.putExtra("loaclFile", uri.toString())
            }

            context?.startActivity(intent)
        }
        holder?.itemView?.setOnLongClickListener {
            mOnLongListener.onLongClick(position)
            true
        }
    }

    private fun getDownloadState(playUrl: String?, holder: DownloadViewHolder?) {
        disposable = RxDownload.getInstance(context).receiveDownloadStatus(playUrl)
                .subscribe { event ->
                    //当事件为Failed时, 才会有异常信息, 其余时候为null.
                    if (event.flag == DownloadFlag.FAILED) {
                        val throwable = event.error
                        Log.w("Error", throwable)
                    }
                    val downloadStatus = event.downloadStatus
                    val percent = downloadStatus.percentNumber
                    if (percent == 100L) {
                        if (!disposable.isDisposed) {
                            disposable.dispose()
                        }
                        hasLoaded = true
                        holder?.iv_download_state?.visibility = View.GONE
                        holder?.tv_detail?.text = "已缓存"
                        isDownload = false
                        SPUtils.getInstance(context!!, "download_state").put(playUrl.toString(), false)
                    } else {
                        if (holder?.iv_download_state?.visibility != View.VISIBLE) {
                            holder?.iv_download_state?.visibility = View.VISIBLE
                        }
                        if (isDownload) {
                            holder?.tv_detail?.text = "缓存中 / $percent%"
                        } else {
                            holder?.tv_detail?.text = "已暂停 / $percent%"
                        }


                    }
                }

    }

    private fun addMission(playUrl: String?, count: Int) {
        RxDownload.getInstance(context).serviceDownload(playUrl, "download$count").subscribe({
            Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(context, "添加任务失败", Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class DownloadViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView = itemView?.findViewById(R.id.iv_photo) as ImageView
        var tv_title: TextView = itemView?.findViewById(R.id.tv_title) as TextView
        var tv_detail: TextView = itemView?.findViewById(R.id.tv_detail) as TextView
        var iv_download_state: ImageView = itemView?.findViewById(R.id.iv_download_state) as ImageView

        init {
            tv_title.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        }
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int)
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener) {
        mOnLongListener = onLongClickListener
    }
}