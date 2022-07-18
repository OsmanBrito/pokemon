package com.example.pokemon.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.data.model.Type
import com.example.pokemon.util.PokemonTypeMapper
import kotlinx.android.synthetic.main.type_pokemon_item.view.*

class PokemonDetailTypeAdapter(private val context: Context, private val types: List<Type>) :
    RecyclerView.Adapter<PokemonDetailTypeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(typeName: String) {
            itemView.pokemon_details_type_color.setCardBackgroundColor(
                Color.parseColor(
                    PokemonTypeMapper.typeToColor(typeName.lowercase())
                )
            )
            itemView.pokemon_details_type_tv.text = typeName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.type_pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(types[position].type.name)
    }

    override fun getItemCount(): Int {
        return types.size
    }
}