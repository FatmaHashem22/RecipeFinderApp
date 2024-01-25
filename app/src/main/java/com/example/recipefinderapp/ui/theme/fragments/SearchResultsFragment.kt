package com.example.recipefinderapp.ui.theme.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.Constants
import com.example.recipefinderapp.R
import com.example.recipefinderapp.adapters.SearchRecipesAdapter
import com.example.recipefinderapp.api.APIManager
import com.example.recipefinderapp.model.RecipesByIngredientsResponse
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
        recipesAdapter = SearchRecipesAdapter(ArrayList())
        recyclerView.adapter = recipesAdapter
        progressBar = view.findViewById(R.id.progressBar)
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
                ).enqueue(object : Callback<RecipesByIngredientsResponse>{
                    override fun onResponse(
                        call: Call<RecipesByIngredientsResponse>,
                        response: Response<RecipesByIngredientsResponse>
                    ) {
                        progressBar.isVisible = false
                        Log.e("OnResponse","${response.body()?.recipes}")
                        recipesAdapter.changeData(response.body()?.recipes!!)
                    }

                    override fun onFailure(call: Call<RecipesByIngredientsResponse>, t: Throwable) {
                        progressBar.isVisible = false
                        Log.e("OnFailure","$t")
                    }

                })
        }



    }


}