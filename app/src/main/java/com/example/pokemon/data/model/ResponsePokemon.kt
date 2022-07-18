package com.example.pokemon.data.model

import java.io.Serializable

data class ResponsePokemon(
    val count: Long,
    val next: String,
    val previous: Any?,
    val results: List<Result>,
) : Serializable {
    fun getNextOffset(): Int {
        return next.substring(next.length - 11, next.length - 9).toInt()
    }
}
