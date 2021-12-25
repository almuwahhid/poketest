package com.bobobox.poketest.resources.data.repository

import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.AbilityData
import com.bobobox.poketest.resources.data.entity.PokemonDetail.PokemonDetail
import com.bobobox.poketest.resources.data.mapper.getPokemonOffset
import com.bobobox.poketest.resources.data.source.remote.api.PokemonAPI
import com.bobobox.poketest.resources.util.network.parse
import com.tokopedia.core.util.network.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class OnlinePokeRepository(val pokemonAPI: PokemonAPI) : IOnlinePokeRepository {
    override suspend fun getPokemonList(offset: Int): Common<Pair<List<Pokemon>, String>, Pair<Int, String>> = withContext(Dispatchers.IO) {
        return@withContext pokemonAPI.pokemonList(offset = offset).parse().fold(
            fnL = {
                Common.Left(Pair(it.results?: arrayListOf(), it.next.getPokemonOffset()))
            },
            fnR = {
                Common.Right(it)
            }
        )
    }

    override suspend fun getPokemonDetail(id: Int): Common<PokemonDetail, Pair<Int, String>> = withContext(Dispatchers.IO) {
        return@withContext pokemonAPI.pokemonDetail(id = id).parse().fold(
            fnL = {
                Common.Left(it)
            },
            fnR = {
                Common.Right(it)
            }
        )
    }

    override suspend fun getPokemonAbility(ids: List<Int>): Flow<AbilityData.Ability> {
        return flow {
            for(id in ids) {
                emit(pokemonAPI.ability(id))
            }
        }.flowOn(Dispatchers.IO)
    }
}


interface IOnlinePokeRepository{
    // TODO : First pair to get list data & offset, second pair to get error code and its message
    suspend fun getPokemonList(offset : Int) : Common<Pair<List<Pokemon>, String>, Pair<Int, String>>
    // TODO : First pair to get error code and its message
    suspend fun getPokemonDetail(id : Int) : Common<PokemonDetail, Pair<Int, String>>
    suspend fun getPokemonAbility(ids : List<Int>) : Flow<AbilityData.Ability>
}