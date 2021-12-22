package com.bobobox.poketest.resources.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.source.local.db.converter.PokemonConverters
import com.bobobox.poketest.resources.data.source.local.db.dao.FavPokemonDao

@Database(
        entities = [FavPokemon::class],
        version = 1, exportSchema = false
)

@TypeConverters(
    PokemonConverters::class
)
abstract class PokeDB : RoomDatabase() {
    abstract val favPOkemonDao: FavPokemonDao
}