package com.example.pokemon.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.data.model.ResponsePokemonDetails
import com.example.pokemon.ui.view.PokemonDetailActivity
import com.example.pokemon.util.Constants
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonListAdapter(private val context: Context) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var pokemons: ArrayList<ResponsePokemonDetails> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: ResponsePokemonDetails) {
            val name = itemView.pokemon_name_tv
            val ownerImage = itemView.pokemon_photo_image
            name.text = pokemon.name
            Glide.with(itemView).load(pokemon.sprites.frontDefault).into(ownerImage)
        }
    }

    fun add(repositories: ArrayList<ResponsePokemonDetails>) {
        pokemons = repositories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.card_view.setOnClickListener {
            val i = Intent(holder.itemView.context, PokemonDetailActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra(Constants.INTENT_EXTRA_POKEMON_DETAILS, pokemons[position])
            holder.itemView.context.startActivity(i)
        }
        holder.bindView(pokemons[position])
    }

}