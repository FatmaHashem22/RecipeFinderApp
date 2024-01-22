package com.example.recipefinderapp.ui.theme

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.capitalize
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.Constants
import com.example.recipefinderapp.R
import com.example.recipefinderapp.adapters.RandomRecipesAdapter
import com.example.recipefinderapp.api.APIManager
import com.example.recipefinderapp.model.RandomRecipesResponse
import com.example.recipefinderapp.model.RecipesItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var tabLayout : TabLayout
    lateinit var randomRecipesAdapter: RandomRecipesAdapter
    lateinit var randomRecipesRecyclerView: RecyclerView

    lateinit var progressBar: ProgressBar
    var categoriesArr = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Tag","Error after creating main")
        initViews()
        getRandoms()


    }

    fun getRandoms() {
        progressBar.isVisible = true
        APIManager
            .getAPIs()
            .getRandomRecipes(
                Constants.API_KEY
            ).enqueue(object : Callback<RandomRecipesResponse> {
                override fun onResponse(
                    call: Call<RandomRecipesResponse>,
                    response: Response<RandomRecipesResponse>
                ) {
                    progressBar.isVisible = false
                    Log.e("OnResponse","${response.body()?.recipes}")
                    randomRecipesAdapter.changeData(response.body()?.recipes!!)
                }

                override fun onFailure(call: Call<RandomRecipesResponse>, t: Throwable) {
                    progressBar.isVisible = false
                    Log.e("OnFailure","$t")
                    Toast.makeText(this@MainActivity,"Something went wrong, please try again later!",Toast.LENGTH_LONG)
                }

            })
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.dish_types_tab)
        randomRecipesRecyclerView = findViewById(R.id.random_recipes_recycler_view)
        randomRecipesAdapter = RandomRecipesAdapter(listOf())
        randomRecipesRecyclerView.adapter = randomRecipesAdapter
        progressBar = findViewById(R.id.progressBar)
        categoriesArr.addAll(listOf("main course", "side dish", "bread", "breakfast", "marinade","fingerfood",
            "appetizer","soup","snack","salad","sauce","dessert","beverage","drink"))
        addTabs(categoriesArr)

    }

    private fun addTabs(tabs: List<String?>) {

        tabs.forEach{ tab ->
            val newTab = tabLayout.newTab()
            newTab.text = tab
            tabLayout.addTab(newTab)
        }
    }


}

