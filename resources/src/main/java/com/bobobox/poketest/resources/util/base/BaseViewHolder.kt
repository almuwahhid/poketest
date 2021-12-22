package com.bobobox.poketest.resources.util.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(v : ViewBinding) :
    RecyclerView.ViewHolder(v.root) {
    abstract fun onBind(index: Int, count: Int, item: T, callback: BaseAdapter.OnItemClick<T>?)
    abstract fun onBindLoading()
}