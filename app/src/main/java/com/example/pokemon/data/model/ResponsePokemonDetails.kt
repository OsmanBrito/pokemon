package com.example.pokemon.data.model

import java.io.Serializable

data class ResponsePokemonDetails (
    val abilities: List<Ability>,
    val height: Long,
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long
): Serializable {
    fun getSkills(): ArrayList<String> {
        val skills: ArrayList<String> = arrayListOf()
        for (ability: Ability in abilities) {
            skills.add(ability.ability.name)
        }
        return skills
    }
}

