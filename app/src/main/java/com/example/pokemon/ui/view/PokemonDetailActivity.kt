package com.example.pokemon.ui.view

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.data.model.ResponsePokemonDetails
import com.example.pokemon.ui.adapter.PokemonDetailTypeAdapter
import com.example.pokemon.util.Constants
import com.example.pokemon.util.PokemonTypeMapper
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.activity_pokemon_details.*

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        setView()
    }

    private fun setView() {
        val details = intent.getSerializableExtra(Constants.INTENT_EXTRA_POKEMON_DETAILS) as? ResponsePokemonDetails
        constraint_activity.setBackgroundColor(
            Color.parseColor(
                PokemonTypeMapper.typeToColor(details!!.types[0].type.name)
            )
        )
        constraint_activity_details.setBackgroundColor(
            Color.parseColor(
                PokemonTypeMapper.typeToColor(details.types[0].type.name)
            )
        )
        val layoutManager = FlexboxLayoutManager(this).apply {
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        recyclerViewTypePokemon.adapter = PokemonDetailTypeAdapter(this, details.types)
        recyclerViewTypePokemon.layoutManager = layoutManager
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, details.getSkills())
        listViewSkills.adapter = arrayAdapter
        tvName.text = details.name.uppercase()
        tvHeight.text = getString(R.string.height_in_hectograms, details.height.toString())
        tvWeight.text = getString(R.string.weight_in_kg, (details.weight / 10).toString())
        tvAttack.text = details.stats[1].baseStat.toString()
        tvHP.text = details.stats[0].baseStat.toString()
        Glide.with(this).load(details.sprites.frontDefault).into(icon)
    }
}