package com.example.recipefinderapp.api

import com.example.recipefinderapp.model.RandomRecipesResponse
import com.example.recipefinderapp.model.RecipeDetailsResponse
import com.example.recipefinderapp.model.RecipesByIngredientsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {

    @GET("recipes/random?number=99")
    fun getRandomRecipes(
        @Query("apiKey") apiKeyAuthentication : String
    ) : Call<RandomRecipesResponse>

    @GET("recipes/random?number=99")
    fun getRandomTypeRecipes(
        @Query("apiKey") apiKeyAuthentication : String,
        @Query("tags") tags : String
    ) : Call<RandomRecipesResponse>

    @GET("recipes/findByIngredients?number=99")
    fun  getRecipesByIngredients(
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("ingredients") ingredients: String
    ) : Call<List<RecipesByIngredientsResponse>>

    @GET("recipes/{id}/information")
    fun getRecipeDetails(
        @Path("id") recipeID : Int,
        @Query("apiKey") apiKeyAuthentication: String

    ) : Call<RecipeDetailsResponse>
}