package com.bobobox.poketest.app.detail.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobobox.poketest.R
import com.bobobox.poketest.databinding.AdapterAvatarBinding
import com.bobobox.poketest.resources.util.base.BaseAdapter
import com.bobobox.poketest.resources.util.base.BaseViewHolder
import com.bobobox.poketest.resources.util.base.BaseViewModel
import com.bumptech.glide.Glide

// TODO : BaseAdapter with databinding supported
class AvatarAdapter : BaseAdapter<String, AdapterAvatarBinding>(){
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterAvatarBinding
        get() = AdapterAvatarBinding::inflate

    override fun itemViewHolder(v: AdapterAvatarBinding): RecyclerView.ViewHolder {
        return AvatarViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        index: Int,
        count: Int,
        item: String,
        callback: OnItemClick<String>?
    ) {
        (holder as AvatarViewHolder).onBind(index, count, item, callback)
    }

    override fun onBindLoading(holder: RecyclerView.ViewHolder) {

    }

    class AvatarViewHolder(val binding : AdapterAvatarBinding) : BaseViewHolder<String>(binding) {
        override fun onBind(index: Int, count: Int, item: String, callback: OnItemClick<String>?): Unit = with(binding) {
            Glide.with(root)
                .load(item)
                .placeholder(R.drawable.ic_pokemon_placeholder  )
                .into(imgAvatar)
        }

        override fun onBindLoading() {

        }

    }
}