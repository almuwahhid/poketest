package com.bobobox.poketest.app.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bobobox.poketest.app.detail.PokemonDetailActivity
import com.bobobox.poketest.app.home.adapter.HomeAdapter
import com.bobobox.poketest.app.home.adapter.HomeFavAdapter
import com.bobobox.poketest.databinding.ActivityMainBinding
import com.bobobox.poketest.resources.GlobalConfig.KEY_INTENT
import com.bobobox.poketest.resources.data.mapper.toFavorite
import com.bobobox.poketest.resources.databinding.HelperBinding
import com.bobobox.poketest.resources.util.base.BaseActivity
import com.bobobox.poketest.resources.util.ext.toJson
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO : BaseActivity with databinding supported
class HomeActivity : BaseActivity<ActivityMainBinding>() {
    override fun initView(binding: ActivityMainBinding): Unit = with(binding){
        viewpager.adapter = Tab(supportFragmentManager)
        viewpager.setOffscreenPageLimit(2)
        tablayout.setupWithViewPager(viewpager)

        tablayout.getTabAt(0)!!.setText("Pokemon")
        tablayout.getTabAt(1)!!.setText("Favorite")
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun observeViewModel() {

    }

    private class Tab(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            if(position == 0){
                return HomeFragment.newInstance(false)
            } else {
                return HomeFragment.newInstance(true)
            }

        }

        override fun getCount(): Int {
            return 2
        }
    }
}