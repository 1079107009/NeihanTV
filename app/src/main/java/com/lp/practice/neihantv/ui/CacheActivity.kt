package com.lp.practice.neihantv.ui

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.adapter.DownloadAdapter
import com.lp.practice.neihantv.mvp.model.bean.VideoBean
import com.lp.practice.neihantv.utils.ObjectSaveUtils
import com.lp.practice.neihantv.utils.SPUtils
import kotlinx.android.synthetic.main.activity_watch.*
import zlc.season.rxdownload2.RxDownload

/**
 * Created by LiPin on 2017/8/30 9:53.
 * 描述：
 */

class CacheActivity : AppCompatActivity() {

    var mList = ArrayList<VideoBean>()
    lateinit var mAdapter: DownloadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = DownloadAdapter(this, mList)
        mAdapter.setOnLongClickListener(object : DownloadAdapter.OnLongClickListener {
            override fun onLongClick(position: Int) {
                addDialog(position)
            }
        })
        recyclerView.adapter = mAdapter
        initData()
    }

    private fun initData() {
        var count: Int = SPUtils.getInstance(this, "downloads").getInt("count")
        var i = 1
        while (i <= count) {
            i++
            var bean: VideoBean
            if (ObjectSaveUtils.getValue(this, "download$i") == null) {
                continue
            } else {
                bean = ObjectSaveUtils.getValue(this, "download$i") as VideoBean
            }
            mList.add(bean)
        }
        if (mList.size.compareTo(0) == 0) {
            tv_hint.visibility = View.VISIBLE
        } else {
            tv_hint.visibility = View.GONE
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun addDialog(position: Int) {
        var builder = AlertDialog.Builder(this)
        var dialog = builder.create()
        builder.setMessage("是否删除当前视频")
        builder.setNegativeButton("否", {
            dialog, which ->
            dialog.dismiss()
        })
        builder.setPositiveButton("是", {
            dialog, which ->
            dialog.dismiss()
            deleteDownload(position)
        })
        builder.show()
    }

    private fun deleteDownload(position: Int) {
        RxDownload.getInstance(this@CacheActivity).deleteServiceDownload(mList[position].playUrl, true).subscribe()
        SPUtils.getInstance(this, "downloads").put(mList[position].playUrl.toString(), "")
        var count = position + 1
        ObjectSaveUtils.deleteFile("download$count", this)
        mList.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.title = "我的缓存"
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
