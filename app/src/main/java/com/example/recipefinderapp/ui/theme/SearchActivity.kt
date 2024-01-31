package com.example.recipefinderapp.ui.theme

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import com.example.recipefinderapp.ui.theme.fragments.IngredientsListFragment
import com.example.recipefinderapp.ui.theme.fragments.SearchResultsFragment
import com.example.recipefinderapp.ui.theme.ui.theme.RecipeFinderAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var cancelText : TextView
    lateinit var searchView : SearchView
    lateinit var listBtn : Button
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
        searchView.setOnClickListener {
//            listBtn.setBackgroundColor(resources.getColor(R.color.grey))
        }

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (query != null) {
                    getSearchResults(query,true)
                }

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getSearchResults(newText,false)
                }

                return true
            }

        })

        listBtn.setOnClickListener {
            if (listBtn.background.constantState?.equals(resources.getDrawable(R.drawable.ingredient_list_selected).constantState) == true) {
                listBtn.setBackgroundResource(R.drawable.ingredient_list_unselected)
                pushFragment(SearchResultsFragment(),"SearchResultsFragment")

            } else {
                listBtn.setBackgroundResource(R.drawable.ingredient_list_selected)
                pushFragment(IngredientsListFragment(),"IngredientsListFragment")
            }

        }
    }

    fun getSearchResults(query: String, initialSearch : Boolean){
        if (initialSearch){
            val bundle = Bundle()
            bundle.putString("searchQuery", query.toString())
            val myFragment = SearchResultsFragment() // Create the fragment with arguments
            myFragment.arguments = bundle
            Log.e("Passed Search: ", "$query")
            pushFragment(myFragment,"SearchResultsFragment")
        } else {
            val existingFragment = getCurrentFragment()
            existingFragment?.getRecipes(query)
        }

    }

    fun initViews() {
        cancelText = findViewById(R.id.cancel_textView)
        searchView = findViewById(R.id.search_view_bar)
        listBtn = findViewById(R.id.ingredients_list_btn)
//        recyclerView = findViewById(R.id.search_recycler_view)
//        recipesAdapter = SearchRecipesAdapter(ArrayList())
//        recyclerView.adapter = recipesAdapter
//        progressBar = findViewById(R.id.progressBar)

    }

    fun getCurrentFragment() : SearchResultsFragment? {
        val fragmentManager = supportFragmentManager
        return fragmentManager.findFragmentByTag("SearchResultsFragment") as? SearchResultsFragment

    }


    fun pushFragment(fragment: Fragment,tag : String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()



        if (fragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction
                .replace(R.id.search_container_fragment, fragment, tag)
                .commit()
        } else {
            Log.d("SearchActivity", "Fragment already exists")
        }
    }
}