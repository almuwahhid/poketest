package com.bobobox.poketest.resources.data.source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon

@Dao
interface FavPokemonDao {
    @Query("SELECT * FROM FavPokemon")
    fun get(): List<FavPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemon: FavPokemon) : Long

    @Query("DELETE FROM FavPokemon")
    fun removeAll()

    @Query("DELETE FROM FavPokemon WHERE id = :id")
    fun remove(id : Int) : Int

    @Query("SELECT EXISTS(SELECT * FROM FavPokemon WHERE id = :id)")
    fun isExist(id : Int) : Boolean
}