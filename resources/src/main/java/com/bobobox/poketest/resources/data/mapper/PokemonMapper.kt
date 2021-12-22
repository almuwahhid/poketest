package com.bobobox.poketest.resources.data.mapper

import android.net.Uri
import android.util.Log
import com.bobobox.poketest.resources.data.entity.FavPokemon
import com.bobobox.poketest.resources.data.entity.Pokemon
import com.bobobox.poketest.resources.data.entity.PokemonDetail.Sprites

fun String.getPokemonOffset() : String{
    return Uri.parse(this).getQueryParameter("offset")?:"0".ifEmpty { "0" }
}

fun String.getPokemonId() : Int? {
    try {
        return Uri.parse(this).lastPathSegment!!.toInt()
    } catch (e : Exception) {
        return null
    }
}

fun Pokemon.toFavorite() : FavPokemon {
    return FavPokemon(
        id = url.getPokemonId()?:0
    ).apply {
        name = this@toFavorite.name
        url = this@toFavorite.url
    }
}

fun Sprites.toListedAvatar() : MutableList<String> {
    val res = ArrayList<String>()
    frontDefault?.let {
        res.add(it)
    }
    frontFemale?.let {
        res.add(it)
    }
    frontShiny?.let {
        res.add(it)
    }
    frontShinyFemale?.let {
        res.add(it)
    }
    backDefault?.let {
        res.add(it)
    }
    backFemale?.let {
        res.add(it)
    }
    backShiny?.let {
        res.add(it)
    }
    backShinyFemale?.let {
        res.add(it)
    }
    return res
}