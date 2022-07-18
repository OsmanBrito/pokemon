package com.example.pokemon.data.api

import com.example.pokemon.data.model.ResponsePokemon
import com.example.pokemon.data.model.ResponsePokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/{name}")
    suspend fun getDetails(@Path(value = "name") name: String): Response<ResponsePokemonDetails>

    //    ?offset=20&limit=20
//    suspend fun repositories(@Query("q") q: String, @Query("sort") sort: String, @Query("page") page: Int): ResponseApi
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Response<ResponsePokemon>
}