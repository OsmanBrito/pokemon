package com.example.pokemon.data.model

import java.io.Serializable

data class Type (
    val slot: Long,
    val type: Species
): Serializable