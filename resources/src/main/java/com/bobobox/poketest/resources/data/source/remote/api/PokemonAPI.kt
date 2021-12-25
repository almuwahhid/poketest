package com.bobobox.poketest.resources.data.source.remote.api

import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.AbilityData
import com.bobobox.poketest.resources.data.entity.PokemonDetail.PokemonDetail
import com.bobobox.poketest.resources.util.network.ListResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface PokemonAPI {
    @GET("pokemon")
    suspend fun pokemonList(
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int
    ): Response<ListResult<List<Pokemon>>>

    @GET("pokemon/{id}")
    suspend fun pokemonDetail(
        @Path("id") id: Int = 0,
    ): Response<PokemonDetail>

    @GET("ability/{id}")
    suspend fun ability(
        @Path("id") id: Int = 0,
    ): AbilityData.Ability
}

