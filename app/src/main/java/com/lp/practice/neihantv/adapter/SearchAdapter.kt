package com.lp.practice.neihantv.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayoutManager
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.ui.ResultActivity

/**
 * Created by LiPin on 2017/8/25 15:52.
 * 描述：
 */
class SearchAdapter(context: Context, list: ArrayList<String>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var context: Context? = null
    var list: ArrayList<String>? = null
    var inflater: LayoutInflater? = null
    var mDialogListener: onDialogDismiss? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        holder?.tv_title?.text = list!![position]
        val params = holder?.tv_title?.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            (holder.tv_title.layoutParams as FlexboxLayoutManager.LayoutParams).flexGrow = 1.0f
        }
        holder?.itemView?.setOnClickListener {
            val keyWord = list?.get(position)
            val intent: Intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("keyWord", keyWord)
            context?.startActivity(intent)
            mDialogListener?.onDismiss()
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val itemView = inflater?.inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(itemView, context!!)
    }

    interface onDialogDismiss {
        fun onDismiss()
    }

    fun setOnDialogDismissListener(onDialogDismiss: onDialogDismiss) {
        mDialogListener = onDialogDismiss
    }

    class SearchViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView = itemView?.findViewById(R.id.tv_title) as TextView

    }
}