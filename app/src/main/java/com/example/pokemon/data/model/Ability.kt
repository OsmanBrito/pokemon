package com.example.pokemon.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Ability (
    val ability: Species,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    val slot: Long
): Serializable