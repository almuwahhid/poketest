package com.bobobox.poketest.resources.data.entity.PokemonDetail


import com.google.gson.annotations.SerializedName

data class AbilityData(
    @SerializedName("ability")
    val ability: Ability? = null,
    @SerializedName("is_hidden")
    val isHidden: Boolean? = null,
    @SerializedName("slot")
    val slot: Int? = null
) {
    data class Ability(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("generation")
        val generation: Generation? = null
    )

    data class Generation(@SerializedName("name")
                          val name: String? = null,
                          @SerializedName("url")
                          val url: String? = null)
}