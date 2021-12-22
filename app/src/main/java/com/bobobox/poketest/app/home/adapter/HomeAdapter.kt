package com.bobobox.poketest.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobobox.poketest.databinding.AdapterHomeBinding
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.util.base.BaseAdapter
import com.bobobox.poketest.resources.util.base.BaseViewHolder

// TODO : BaseAdapter with databinding supported
class HomeAdapter : BaseAdapter<Pokemon, AdapterHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterHomeBinding
        get() = AdapterHomeBinding::inflate

    override fun itemViewHolder(v: AdapterHomeBinding): RecyclerView.ViewHolder {
        return HomeViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        index: Int,
        count: Int,
        item: Pokemon,
        callback: OnItemClick<Pokemon>?
    ) {
        (holder as HomeViewHolder).onBind(index, count, item, callback)
    }

    override fun onBindLoading(holder: RecyclerView.ViewHolder) {
        (holder as HomeViewHolder).onBindLoading()
    }

    class HomeViewHolder(val binding: AdapterHomeBinding) : BaseViewHolder<Pokemon>(binding) {
        override fun onBind(
            index: Int,
            count: Int,
            item: Pokemon,
            callback: OnItemClick<Pokemon>?
        ) = with(binding) {
            adapter.visibility = View.VISIBLE
            loading.visibility = View.GONE
            tvMonster.text = item.name

            adapter.setOnClickListener {
                callback?.onClick(item)
            }
        }

        override fun onBindLoading() = with(binding) {
            adapter.visibility = View.GONE
            loading.visibility = View.VISIBLE
        }
    }

}