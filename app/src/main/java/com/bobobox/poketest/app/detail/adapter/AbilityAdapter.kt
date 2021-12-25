package com.bobobox.poketest.app.detail.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bobobox.poketest.databinding.AdapterAbilityBinding
import com.bobobox.poketest.resources.data.entity.PokemonDetail.AbilityData
import com.bobobox.poketest.resources.util.base.BaseAdapter
import com.bobobox.poketest.resources.util.base.BaseViewHolder
import com.bobobox.poketest.resources.util.ext.toJson

class AbilityAdapter : BaseAdapter<AbilityData.Ability, AdapterAbilityBinding>(){
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterAbilityBinding
        get() = AdapterAbilityBinding::inflate

    override fun itemViewHolder(v: AdapterAbilityBinding): RecyclerView.ViewHolder {
        return AbilityViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        index: Int,
        count: Int,
        item: AbilityData.Ability,
        callback: OnItemClick<AbilityData.Ability>?
    ) {
        (holder as AbilityViewHolder).onBind(index, count, item, callback)
    }

    override fun onBindLoading(holder: RecyclerView.ViewHolder) {

    }

    class AbilityViewHolder(val binding: AdapterAbilityBinding) : BaseViewHolder<AbilityData.Ability>(binding) {
        override fun onBind(
            index: Int,
            count: Int,
            item: AbilityData.Ability,
            callback: OnItemClick<AbilityData.Ability>?
        ) = with(binding) {
            tvName.text = item.name
            item.generation?.let {
                tvGeneration.text = "gen : ${it.name}"
            }?:fun() {
                tvGeneration.text = "gen : ~"
            }()
        }

        override fun onBindLoading() {

        }
    }

    fun updateAbility(ability: AbilityData.Ability) {
        Log.d("ability", "updateAbility: ${ability.toJson()}")
        data.get(data.indexOfFirst { it.id == ability.id }).generation = ability.generation
        notifyDataSetChanged()
    }

}