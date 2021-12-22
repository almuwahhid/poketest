package com.bobobox.poketest.app.detail.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobobox.poketest.databinding.AdapterHomeBinding
import com.bobobox.poketest.databinding.AdapterMovesBinding
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.Move
import com.bobobox.poketest.resources.util.base.BaseAdapter
import com.bobobox.poketest.resources.util.base.BaseViewHolder

// TODO : BaseAdapter with databinding supported
class MovesAdapter : BaseAdapter<Move, AdapterMovesBinding>(){
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterMovesBinding
        get() = AdapterMovesBinding::inflate

    override fun itemViewHolder(v: AdapterMovesBinding): RecyclerView.ViewHolder {
        return MovesViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        index: Int,
        count: Int,
        item: Move,
        callback: OnItemClick<Move>?
    ) {
        (holder as MovesViewHolder).onBind(index, count, item, callback)
    }

    override fun onBindLoading(holder: RecyclerView.ViewHolder) {

    }

    class MovesViewHolder(val binding: AdapterMovesBinding) : BaseViewHolder<Move>(binding) {
        override fun onBind(index: Int, count: Int, item: Move, callback: OnItemClick<Move>?) = with(binding) {
            tvMove.text = item.move?.name?:"-"
        }

        override fun onBindLoading() {

        }

    }
}