package com.bobobox.poketest.resources.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// TODO : Separate to favPokemon and Pokemon to differentiate a data structure
@Entity
data class FavPokemon (
    @PrimaryKey(autoGenerate = false) @SerializedName("id")
    var id: Int = 0,
    @SerializedName("flag")
    var flag: Boolean = true
) : Pokemon()