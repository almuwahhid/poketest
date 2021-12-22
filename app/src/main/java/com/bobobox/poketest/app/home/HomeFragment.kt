package com.bobobox.poketest.app.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobobox.poketest.R
import com.bobobox.poketest.app.detail.PokemonDetailActivity
import com.bobobox.poketest.app.home.adapter.HomeAdapter
import com.bobobox.poketest.app.home.adapter.HomeFavAdapter
import com.bobobox.poketest.databinding.FragmentHomeBinding
import com.bobobox.poketest.resources.GlobalConfig
import com.bobobox.poketest.resources.data.mapper.toFavorite
import com.bobobox.poketest.resources.databinding.HelperBinding
import com.bobobox.poketest.resources.util.base.BaseFragment
import com.bobobox.poketest.resources.util.ext.toJson
import com.bobobox.poketest.resources.widget.RecyclerScroll
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO : BaseFragment with databinding supported
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    var favorited = false
    val adapter : HomeAdapter by lazy {
        HomeAdapter()
    }.also {
        it.value.setItemCallback { pokemon ->
            startActivity(
                Intent(requireContext(), PokemonDetailActivity::class.java).putExtra(GlobalConfig.KEY_INTENT, pokemon.toFavorite().toJson())
            )
        }
    }

    val favAdapter : HomeFavAdapter by lazy {
        HomeFavAdapter()
    }.also {
        it.value.setItemCallback { favPokemon ->
            startActivity(
                Intent(requireContext(), PokemonDetailActivity::class.java).putExtra(GlobalConfig.KEY_INTENT, favPokemon.toJson())
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(isFavorite : Boolean) =
            HomeFragment().apply {
                favorited = isFavorite
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun observeViewModel()  = with(viewModel){
        loading.observe(viewLifecycleOwner, {
            initLoadingByTabPosition(it)
        })
        monsters.observe(viewLifecycleOwner, {
            adapter.add(it.second, it.first)

            if(it.first.isEmpty()) (binding.helper as HelperBinding).helperNodata.visibility = View.VISIBLE
        })
        favmonsters.observe(viewLifecycleOwner, {
            favAdapter.add(true, it)

            if(it.isEmpty()) (binding.helper as HelperBinding).helperNodata.visibility = View.VISIBLE
        })
    }

    override fun initView(binding: FragmentHomeBinding) = with(binding) {
        if(favorited) rv.adapter = favAdapter
        else {
            rv.adapter = adapter
            rv.addOnScrollListener(object : RecyclerScroll(rv.layoutManager as LinearLayoutManager) {
                override fun show() {

                }

                override fun hide() {

                }

                override fun loadMore() {
                    requestDataByFavorited(favorited)
                }
            })
        }

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            refresh()
        }

        if(!favorited) refresh()
    }

    private fun refresh() {
        viewModel.requiredRefresh {
            requestDataByFavorited(favorited)
        }
    }

    private fun requestDataByFavorited(isFavorite : Boolean) {
        if(isFavorite) viewModel.getFavMonsters()
        else viewModel.getMonsters()
    }

    private fun initLoadingByTabPosition(isloading : Boolean) = with(binding){
        if(favorited) favAdapter.initLoading(isloading)
        else adapter.initLoading(isloading)

        if(isloading) helper.helperNodata.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        if(favorited) refresh()
    }
}