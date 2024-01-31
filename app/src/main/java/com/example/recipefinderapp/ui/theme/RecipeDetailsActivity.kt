package com.example.recipefinderapp.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipefinderapp.Constants
import com.example.recipefinderapp.R
import com.example.recipefinderapp.adapters.IngredientItemAdapter
import com.example.recipefinderapp.api.APIManager
import com.example.recipefinderapp.model.RecipeDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailsActivity : ComponentActivity() {

    lateinit var recipeName : TextView
    lateinit var recipeImage : ImageView
    lateinit var recipeTime : TextView
    lateinit var recipeServing : TextView
    lateinit var recipeSaved : TextView
    lateinit var ingredientsRecyclerView: RecyclerView
    lateinit var ingredientAdapter : IngredientItemAdapter
    lateinit var summary : TextView
    lateinit var instructions : TextView
    lateinit var close : CardView
    var recipeId : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_item_details)

        initViews()
        recipeId = intent.getIntExtra("id",-1)
        Log.e("Received ID: ","$recipeId")
        getDetails(recipeId)

    }

    private fun getDetails(id : Int?) {
        if (id != -1 && id != null){
            APIManager
                .getAPIs()
                .getRecipeDetails(
                    id,
                    Constants.API_KEY
                ).enqueue(object : Callback<RecipeDetailsResponse> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<RecipeDetailsResponse>,
                        response: Response<RecipeDetailsResponse>
                    ) {
                        recipeName.text = response.body()?.title
                        Glide.with(recipeImage).load(response.body()?.image).centerCrop().into(recipeImage)
                        recipeTime.text = response.body()?.readyInMinutes.toString()+" MINS"
                        recipeServing.text = response.body()?.servings.toString()+" PERSONS"

                        summary.text = HtmlCompat.fromHtml(response.body()?.summary!!,HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                        instructions.text = HtmlCompat.fromHtml(response.body()?.instructions!!,HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                        val glutenFree = response.body()?.glutenFree
                        if (glutenFree!!) {
                            recipeSaved.text = "Gluten Free"
                        } else{
                            recipeSaved.text = "Gluten"
                        }
                        Log.e("onResponse","${response.body()?.title}")
                        ingredientAdapter.changeData(response.body()?.extendedIngredients)


                    }

                    override fun onFailure(call: Call<RecipeDetailsResponse>, t: Throwable) {
                        Log.e("onFailure","$t")
                    }

                })
        } else {
            Log.e("Tag: ","id is -1")
        }
    }


    private fun initViews() {
        recipeName = findViewById(R.id.recipe_title)
        recipeImage = findViewById(R.id.recipe_image)
        recipeTime = findViewById(R.id.details_prepare_time)
        recipeServing = findViewById(R.id.details_servings)
        recipeSaved = findViewById(R.id.details_saved)
        summary = findViewById(R.id.recipe_summary)
        instructions = findViewById(R.id.recipe_instructions)
        ingredientsRecyclerView = findViewById(R.id.details_ingredients_recycler_view)
        ingredientAdapter = IngredientItemAdapter(listOf())
        ingredientsRecyclerView.adapter = ingredientAdapter
        close = findViewById(R.id.close_page)
        close.setOnClickListener{
            finish()
        }
    }
}