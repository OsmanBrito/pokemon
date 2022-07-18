package com.example.pokemon.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Stat (
    @SerializedName("base_stat")
    val baseStat: Long,

    val effort: Long,
    val stat: Species
): Serializable