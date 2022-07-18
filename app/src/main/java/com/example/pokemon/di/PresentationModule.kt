package com.example.pokemon.di

import com.example.pokemon.data.repository.PokemonRepository
import com.example.pokemon.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    val presentationModuleFlavor =  module {
        factory { PokemonRepository() }
        viewModel { PokemonViewModel(get()) }
    }

}