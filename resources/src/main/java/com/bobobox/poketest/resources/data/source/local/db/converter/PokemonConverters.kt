package com.bobobox.poketest.resources.data.source.local.db.converter

import androidx.room.TypeConverter
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonConverters {
    @TypeConverter
    fun stringToPokemon(json: String): FavPokemon? {
        val gson = Gson()
        val type = object : TypeToken<FavPokemon>() {}.type
        return gson.fromJson(json, type)
    }
}