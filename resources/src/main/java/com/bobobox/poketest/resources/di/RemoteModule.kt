package com.bobobox.poketest.resources.di

import com.bobobox.poketest.resources.data.source.remote.APIManager
import com.bobobox.poketest.resources.util.network.NetworkUtil
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val remoteModule = module {
    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        NetworkUtil.okHttpClient()
    }

    single {
        NetworkUtil.retrofit(get(), get())
    }

    single {
        APIManager.providePokemon(get())
    }

}