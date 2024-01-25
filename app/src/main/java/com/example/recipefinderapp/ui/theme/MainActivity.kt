package com.example.recipefinderapp.ui.theme

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
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
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var tabLayout : TabLayout
    lateinit var randomRecipesAdapter: RandomRecipesAdapter
    lateinit var randomRecipesRecyclerView: RecyclerView
    lateinit var searchCardView : CardView

    lateinit var progressBar: ProgressBar
    var categoriesArr = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Tag","Error after creating main")
        initViews()
        getRandoms("All")
        initListeners()
    }

//    fun getRecipesByIngredients(searchQuery : String) {
//        progressBar.isVisible = true
//        val parsedQuery = searchQuery.split("+").map { it.trim() }
//        APIManager
//            .getAPIs()
//            . getComplexSearchResults(
//                Constants.API_KEY,
//                parsedQuery
//            ).enqueue(object : Callback<RandomRecipesResponse> {
//                override fun onResponse(
//                    call: Call<RandomRecipesResponse>,
//                    response: Response<RandomRecipesResponse>
//                ) {
//                    progressBar.isVisible = false
//                    Log.e("OnResponse","${response.body()?.recipes}")
//                    randomRecipesAdapter.changeData(response.body()?.recipes!!)
//                }
//
//                override fun onFailure(call: Call<RandomRecipesResponse>, t: Throwable) {
//                    progressBar.isVisible = false
//                    Log.e("OnFailure","$t")
//                    Toast.makeText(this@MainActivity,"Something went wrong, please try again later!",Toast.LENGTH_LONG)
//                }
//
//            })
//    }


    fun getRandoms(tags : String) {
        progressBar.isVisible = true

        if (tags != "All") {
            APIManager
                .getAPIs()
                .getRandomTypeRecipes(
                    Constants.API_KEY,
                    tags.lowercase()
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
        else {
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


    }

    private fun initListeners() {

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val type = tab!!.tag as String
                getRandoms(type)
                val layoutManager =randomRecipesRecyclerView.layoutManager
                layoutManager?.scrollToPosition(0)



            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {


            }

        })

//        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
//                    getRandoms(query)
//                    val layoutManager =randomRecipesRecyclerView.layoutManager
//                    layoutManager?.scrollToPosition(0)
//                }
//                return true
//
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })

        searchCardView.setOnClickListener{
            val intent = Intent(this@MainActivity,SearchActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initViews() {
        tabLayout = findViewById(R.id.dish_types_tab)
        randomRecipesRecyclerView = findViewById(R.id.random_recipes_recycler_view)
        randomRecipesAdapter = RandomRecipesAdapter(listOf())
        randomRecipesRecyclerView.adapter = randomRecipesAdapter
        progressBar = findViewById(R.id.progressBar)
        categoriesArr.addAll(listOf("All", "Appetizer", "Beverage", "Bread", "Breakfast", "Dessert", "Drink",
            "Fingerfood", "Main Course", "Marinade","Salad","Sauce","Side Dish", "Snack","Soup"
        ))
        addTabs(categoriesArr)

        searchCardView = findViewById(R.id.card_view_search)



    }

    private fun addTabs(tabs: List<String?>) {

        tabs.forEach{ tab ->
            val newTab = tabLayout.newTab()
            newTab.text = tab
            newTab.tag = tab ?: ""
            tabLayout.addTab(newTab,false)
        }
    }


}