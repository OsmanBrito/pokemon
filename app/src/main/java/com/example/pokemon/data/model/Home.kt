package com.example.pokemon.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Home (
    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_female")
    val frontFemale: Any? = "",
): Serializable