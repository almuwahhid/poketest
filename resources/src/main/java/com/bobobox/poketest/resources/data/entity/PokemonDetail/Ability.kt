package com.bobobox.poketest.resources.data.entity.PokemonDetail


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability")
    val ability: Data? = null,
    @SerializedName("is_hidden")
    val isHidden: Boolean? = null,
    @SerializedName("slot")
    val slot: Int? = null
) {
    data class Data(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("url")
        val url: String? = null
    )
}