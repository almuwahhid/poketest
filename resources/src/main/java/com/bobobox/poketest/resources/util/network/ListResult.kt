package com.bobobox.poketest.resources.util.network

import com.google.gson.annotations.SerializedName

data class ListResult<out T>(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val previous: String = "",
    @SerializedName("results")
    val results: T? = null
)