package com.example.pokemon.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sprites (
    @SerializedName("front_default")
    val frontDefault: String,

    val other: Other
): Serializable