package com.example.pokemon

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokemon.ui.view.PokemonsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ScrollInstrumentedTest {

    @Test fun scrollUiTest() {
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPokemons))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPokemons))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(29))
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPokemons))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(45))
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPokemons))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(59))
    }
}