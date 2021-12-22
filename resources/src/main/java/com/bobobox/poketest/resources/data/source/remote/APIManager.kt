package com.bobobox.poketest.resources.data.source.remote

import com.bobobox.poketest.resources.data.source.remote.api.PokemonAPI
import retrofit2.Retrofit

object APIManager {
    fun providePokemon(retrofit: Retrofit): PokemonAPI {
        return retrofit.create(PokemonAPI::class.java)
    }
}