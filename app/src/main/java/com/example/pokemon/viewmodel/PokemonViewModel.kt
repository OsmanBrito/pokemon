package com.example.pokemon.viewmodel

import androidx.lifecycle.*
import com.example.pokemon.data.model.*
import com.example.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val pokemonsFetched: ArrayList<ResponsePokemonDetails> = arrayListOf()
    var isLoading = MutableLiveData<Boolean>()

    internal var pokemons: MutableLiveData<ArrayList<ResponsePokemonDetails>> = MutableLiveData()
    private var offSet: Int = 0

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        try {
            CoroutineScope(Dispatchers.Default).launch {
                launch(Dispatchers.IO) {
                    isLoading.postValue(true)
                    val pokemonsResult = pokemonRepository.getPokemons(offset = offSet.toString())
                    if (pokemonsResult.isSuccessful) {
                        offSet += pokemonsResult.body()!!.getNextOffset()
                        for (result: Result in pokemonsResult.body()?.results!!) {
                            val response = pokemonRepository.getDetails(result.name)
                            pokemonsFetched.add(
                                response.body()!!
                            )
                        }
                    }
                    isLoading.postValue(false)
                    pokemons.postValue(pokemonsFetched)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading.postValue(false)
        }

    }

    fun getPokemons() {
        fetchPokemons()
    }

//    fun getPokemonDetails(name: String) {
//        GlobalScope.launch {
//            isLoading.postValue(true)
//            val pokemonDetail: Response<ResponsePokemonDetails> = pokemonRepository.getDetails(name.lowercase())
//            if (pokemonDetail.isSuccessful) {
//                isLoading.postValue(false)
//                pokemonDetail.body()?.let {
//                    val i = Intent(application.baseContext, PokemonDetailActivity::class.java)
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    i.putExtra("pokemonDetails", it)
//                    application.startActivity(i)
//                }
//            } else {
//                Handler(Looper.getMainLooper()).post {
//                    Toast.makeText(application, "Pokemon not found", Toast.LENGTH_SHORT).show()
//                }
//                isLoading.postValue(false)
//            }
//        }
//    }

}