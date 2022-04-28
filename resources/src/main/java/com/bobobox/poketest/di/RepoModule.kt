package com.bobobox.poketest.di

import com.bobobox.poketest.resources.data.repository.OfflinePokeRepository
import com.bobobox.poketest.resources.data.repository.OnlinePokeRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        OnlinePokeRepository(get())
    }
    single {
        OfflinePokeRepository(get())
    }
}