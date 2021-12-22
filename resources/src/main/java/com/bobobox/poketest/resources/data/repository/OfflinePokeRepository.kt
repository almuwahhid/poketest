package com.bobobox.poketest.resources.data.repository

import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.source.local.db.dao.FavPokemonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OfflinePokeRepository(val favPokemon : FavPokemonDao) : IOfflinePokeRepository{

    override suspend fun addFavoriteMonster(pokemon: FavPokemon) = withContext(Dispatchers.IO) {
        favPokemon.add(pokemon)
    }

    override suspend fun removeFavoriteMonster(pokemon: FavPokemon) = withContext(Dispatchers.IO) {
        favPokemon.remove(pokemon.id)
    }

    override suspend fun getFavoriteMonsters(): List<FavPokemon> = withContext(Dispatchers.IO) {
        return@withContext favPokemon.get()
    }

    override suspend fun isMonsterFavorited(id: Int): Boolean = withContext(Dispatchers.IO) {
        return@withContext favPokemon.isExist(id)
    }

}

interface IOfflinePokeRepository{
    suspend fun addFavoriteMonster(pokemon: FavPokemon)
    suspend fun removeFavoriteMonster(pokemon: FavPokemon)
    suspend fun getFavoriteMonsters() : List<FavPokemon>
    suspend fun isMonsterFavorited(id : Int) : Boolean
}