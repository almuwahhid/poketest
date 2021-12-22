package com.bobobox.poketest.app.di

import com.bobobox.poketest.app.detail.PokemonDetailViewModel
import com.bobobox.poketest.app.home.HomeViewModel
import com.bobobox.poketest.resources.util.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BaseViewModel()
    }

    viewModel {
        HomeViewModel(
            onlineRepo = get(),
            offlineRepo = get()
        )
    }

    viewModel {
        PokemonDetailViewModel(
            onlineRepo = get(),
            offlineRepo = get()
        )
    }

}