package com.example.recipefinderapp.ui.theme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.Constants
import com.example.recipefinderapp.R
import com.example.recipefinderapp.adapters.SearchRecipesAdapter
import com.example.recipefinderapp.api.APIManager
import com.example.recipefinderapp.model.RecipesByIngredientsResponse
import com.example.recipefinderapp.ui.theme.fragments.SearchResultsFragment
import com.example.recipefinderapp.ui.theme.ui.theme.RecipeFinderAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var cancelText : TextView
    lateinit var searchView : SearchView
    lateinit var recyclerView: RecyclerView
    lateinit var recipesAdapter: SearchRecipesAdapter
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initViews()
        initListeners()

    }


    fun initListeners() {
        cancelText.setOnClickListener {
            val intent = Intent(this@SearchActivity,MainActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
//                    val bundle = Bundle()
//                    bundle.putString("searchQuery", query.toString())
//                    val myFragment = SearchResultsFragment() // Create the fragment with arguments
//                    myFragment.arguments = bundle
//                    Log.e("Passed Search: ", "$query")
//                    pushFragment(myFragment) // Use the same instance for the transaction
                    getSearchResults(query)
                }

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    fun getSearchResults(query: String){
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
                ).enqueue(object : Callback<RecipesByIngredientsResponse> {
                    override fun onResponse(
                        call: Call<RecipesByIngredientsResponse>,
                        response: Response<RecipesByIngredientsResponse>
                    ) {
                        progressBar.isVisible = false
                        recyclerView.isVisible = true
//                        Log.e("OnResponse","${response.body()?.recipes}")
//                        recipesAdapter.changeData(response.body()?.recipes!!)
                        val recipes = response.body()?.recipes ?: listOf()  // Handle potential null
                        recipes.forEach { recipe ->
                            Log.e("OnResponse", "$recipe")  // Log each recipe
                        }
                        recipesAdapter.changeData(recipes)
                    }

                    override fun onFailure(call: Call<RecipesByIngredientsResponse>, t: Throwable) {
                        progressBar.isVisible = false
                        Log.e("OnFailure","$t")
                    }

                })
        }

    }

    fun initViews() {
        cancelText = findViewById(R.id.cancel_textView)
        searchView = findViewById(R.id.search_view_bar)
        recyclerView = findViewById(R.id.search_recycler_view)
        recipesAdapter = SearchRecipesAdapter(ArrayList())
        recyclerView.adapter = recipesAdapter
        progressBar = findViewById(R.id.progressBar)
        progressBar.isVisible = false
    }


//    fun pushFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//
//
//        if (fragmentManager.findFragmentByTag("SearchResultsFragment") == null) {
//            fragmentTransaction
//                .replace(R.id.search_container_fragment, fragment, "SearchResultsFragment")
//                .commit()
//        } else {
//            Log.d("SearchActivity", "Fragment already exists")
//        }
//    }
}