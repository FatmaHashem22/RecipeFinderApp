package com.example.recipefinderapp.api

import com.example.recipefinderapp.model.RandomRecipesResponse
import retrofit2.Call
import retrofit2.http.GET
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
}