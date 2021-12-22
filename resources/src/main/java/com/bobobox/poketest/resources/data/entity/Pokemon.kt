package com.bobobox.poketest.resources.data.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
open class Pokemon(
    @SerializedName("name")
    open var name: String = "",
    @SerializedName("url")
    open var url: String = ""
)