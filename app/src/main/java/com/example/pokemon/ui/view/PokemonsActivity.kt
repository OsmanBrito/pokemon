package com.example.pokemon.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.airbnb.lottie.LottieAnimationView
import com.example.pokemon.R
import com.example.pokemon.di.PresentationModule
import com.example.pokemon.ui.adapter.PokemonListAdapter
import com.example.pokemon.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class PokemonsActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModel()
    private lateinit var builder: AlertDialog
    private lateinit var view: View
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        initDI()
        createDialog()
        layoutManager = LinearLayoutManager(this)
        (recyclerViewPokemons.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerViewPokemons.layoutManager = layoutManager
        val adapter = PokemonListAdapter(this)
        recyclerViewPokemons.adapter = adapter
        viewModel.pokemons.observe(this) { pokemons ->
            adapter.add(pokemons)
        }
        viewModel.isLoading.observe(this) {
            if (it) {
                view.findViewById<LottieAnimationView>(R.id.animation_view)!!.repeatCount = 9999
                view.findViewById<LottieAnimationView>(R.id.animation_view)!!.playAnimation()
                builder.show()
            } else {
                view.findViewById<LottieAnimationView>(R.id.animation_view)!!.repeatCount = 0
                view.findViewById<LottieAnimationView>(R.id.animation_view)!!.cancelAnimation()
                builder.dismiss()
            }
        }
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                recyclerView.let { super.onScrollStateChanged(it, newState) }
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.getPokemons()
                    }
                }
            }
        }
        recyclerViewPokemons.addOnScrollListener(scrollListener)
    }

    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    private fun initDI() {
        startKoin {
            modules(PresentationModule.presentationModuleFlavor)
        }
    }

    private fun createDialog() {
        builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        view = layoutInflater.inflate(R.layout.dialog_loading_pokeball, null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(false)
    }

}