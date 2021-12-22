package com.bobobox.poketest.resources.data.entity.PokemonDetail


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: Data? = null
) {
    data class Data(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("url")
        val url: String? = null
    )
}