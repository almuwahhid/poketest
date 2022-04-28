package com.bobobox.poketest.di

import com.bobobox.poketest.resources.data.source.local.db.DbManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { DbManager.provideDatabase(androidApplication()) }
    single { DbManager.provideFavPokemonDao(get()) }
}
