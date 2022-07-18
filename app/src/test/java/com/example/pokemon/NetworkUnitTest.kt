package com.example.pokemon

import com.example.pokemon.data.api.RetrofitInitializer
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class NetworkUnitTest {

    @Test
    fun callSuccess() {
        val apiCall = RetrofitInitializer.pokemonApi
        try {
            runBlocking {
                val response = apiCall.getPokemons("0", "20")
                assertTrue(response.body()?.results!!.size == 20)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}