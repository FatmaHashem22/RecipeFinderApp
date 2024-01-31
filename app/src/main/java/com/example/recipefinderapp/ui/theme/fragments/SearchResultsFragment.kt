package com.example.recipefinderapp.ui.theme.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.Constants
import com.example.recipefinderapp.R
import com.example.recipefinderapp.RecipeClickListener
import com.example.recipefinderapp.adapters.SearchRecipesAdapter
import com.example.recipefinderapp.api.APIManager
import com.example.recipefinderapp.model.RecipesByIngredientsResponse
import com.example.recipefinderapp.ui.theme.RecipeDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recipesAdapter: SearchRecipesAdapter
    lateinit var progressBar: ProgressBar

    var query : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate ","HERE")
        query = arguments?.getString("searchQuery")
        Log.e("OnCreate Query = ","$query")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Fragment : ","created")
        Log.e("Query = ","$query")

        recyclerView = view.findViewById(R.id.search_recycler_view)
        recipesAdapter = SearchRecipesAdapter(listOf())
        recyclerView.adapter = recipesAdapter
        recipesAdapter.onRecipeClickListener = object : RecipeClickListener {
            override fun onRecipeClick(id: Int) {
                val intent = Intent(requireContext(), RecipeDetailsActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }

        }
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.isVisible = false
        getRecipes(query)

        }


    fun getRecipes(query : String?) {
        progressBar.isVisible = true
        if (query != null) {
            Log.e("Before API Call Query = ","$query")
            val parsedQuery = query.split(",")
            val queryString = parsedQuery.joinToString(",")
            APIManager
                .getAPIs()
                .getRecipesByIngredients(
                    Constants.API_KEY,
                    queryString
                ).enqueue(object : Callback<List<RecipesByIngredientsResponse>> {


                    override fun onResponse(
                        call: Call<List<RecipesByIngredientsResponse>>,
                        response: Response<List<RecipesByIngredientsResponse>>
                    ) {
                        progressBar.isVisible = false
                        recyclerView.isVisible = true
//                        Log.e("OnResponse","${response.body()?.recipes}")
//                        recipesAdapter.changeData(response.body()?.recipes!!)
                        val recipes = response.body() // Handle potential null
                        recipes?.forEach { recipe ->
                            Log.e("OnResponse", "$recipe")  // Log each recipe
                        }
                        recipesAdapter.changeData(recipes!!)
                    }

                    override fun onFailure(
                        call: Call<List<RecipesByIngredientsResponse>>,
                        t: Throwable
                    ) {
                        progressBar.isVisible = false
                        Log.e("OnFailure","$t")
                    }

                })
        }


    }


}