package com.example.pokemon.data.repository

import com.example.pokemon.data.api.RetrofitInitializer
import com.example.pokemon.data.model.ResponsePokemon
import com.example.pokemon.data.model.ResponsePokemonDetails
import retrofit2.Response

class PokemonRepository {

    suspend fun getDetails(pokemon: String): Response<ResponsePokemonDetails> {
        return RetrofitInitializer.pokemonApi.getDetails(pokemon)
    }

    suspend fun getPokemons(offset: String): Response<ResponsePokemon>{
        return RetrofitInitializer.pokemonApi.getPokemons(offset, "20")
    }

}