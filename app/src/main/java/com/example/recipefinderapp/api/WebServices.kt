package com.example.recipefinderapp.api

import com.example.recipefinderapp.model.RandomRecipesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("recipes/random?number=95")
    fun getRandomRecipes(
        @Query("apiKey") apiKeyAuthentication : String
    ) : Call<RandomRecipesResponse>
}