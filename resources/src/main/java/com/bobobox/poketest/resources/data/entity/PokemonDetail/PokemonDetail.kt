package com.bobobox.poketest.resources.data.entity.PokemonDetail


import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("abilities")
    val abilityData: List<AbilityData>? = null,
    @SerializedName("base_experience")
    val baseExperience: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("is_default")
    val isDefault: Boolean? = null,
    @SerializedName("moves")
    val moves: List<Move>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("species")
    val species: Species? = null,
    @SerializedName("sprites")
    val sprites: Sprites? = null,
    @SerializedName("weight")
    val weight: Int? = null
)