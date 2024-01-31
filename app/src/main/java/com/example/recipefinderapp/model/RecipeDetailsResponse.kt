package com.example.recipefinderapp.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(

	@field:SerializedName("instructions")
	val instructions: String? = null,

	@field:SerializedName("sustainable")
	val sustainable: Boolean? = null,

	@field:SerializedName("analyzedInstructions")
	val analyzedInstructions: List<Any?>? = null,

	@field:SerializedName("glutenFree")
	val glutenFree: Boolean? = null,

	@field:SerializedName("veryPopular")
	val veryPopular: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("healthScore")
	val healthScore: Any? = null,

	@field:SerializedName("diets")
	val diets: List<Any?>? = null,

	@field:SerializedName("readyInMinutes")
	val readyInMinutes: Int? = null,

	@field:SerializedName("sourceUrl")
	val sourceUrl: String? = null,

	@field:SerializedName("creditsText")
	val creditsText: String? = null,

	@field:SerializedName("dairyFree")
	val dairyFree: Boolean? = null,

	@field:SerializedName("servings")
	val servings: Int? = null,

	@field:SerializedName("vegetarian")
	val vegetarian: Boolean? = null,

	@field:SerializedName("whole30")
	val whole30: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null,

	@field:SerializedName("winePairing")
	val winePairing: WinePairing? = null,

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("veryHealthy")
	val veryHealthy: Boolean? = null,

	@field:SerializedName("vegan")
	val vegan: Boolean? = null,

	@field:SerializedName("cheap")
	val cheap: Boolean? = null,

	@field:SerializedName("dishTypes")
	val dishTypes: List<String?>? = null,

	@field:SerializedName("extendedIngredients")
	val extendedIngredients: List<ExtendedIngredientsItemDetails?>? = null,

	@field:SerializedName("gaps")
	val gaps: String? = null,

	@field:SerializedName("cuisines")
	val cuisines: List<Any?>? = null,

	@field:SerializedName("lowFodmap")
	val lowFodmap: Boolean? = null,

	@field:SerializedName("license")
	val license: String? = null,

	@field:SerializedName("weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int? = null,

	@field:SerializedName("occasions")
	val occasions: List<Any?>? = null,

	@field:SerializedName("spoonacularScore")
	val spoonacularScore: Any? = null,

	@field:SerializedName("pricePerServing")
	val pricePerServing: Any? = null,

	@field:SerializedName("sourceName")
	val sourceName: String? = null,

	@field:SerializedName("spoonacularSourceUrl")
	val spoonacularSourceUrl: String? = null,

	@field:SerializedName("ketogenic")
	val ketogenic: Boolean? = null
)

data class ExtendedIngredientsItemDetails(

	@field:SerializedName("originalName")
	val originalName: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("measures")
	val measures: Measures? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("consitency")
	val consitency: String? = null,

	@field:SerializedName("meta")
	val meta: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("aisle")
	val aisle: String? = null
)

data class MeasuresDetails(

	@field:SerializedName("metric")
	val metric: Metric? = null,

	@field:SerializedName("us")
	val us: Us? = null
)

data class MetricDetails(

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null
)

data class WinePairing(

	@field:SerializedName("productMatches")
	val productMatches: List<ProductMatchesItem?>? = null,

	@field:SerializedName("pairingText")
	val pairingText: String? = null,

	@field:SerializedName("pairedWines")
	val pairedWines: List<String?>? = null
)

data class ProductMatchesItem(

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("averageRating")
	val averageRating: Any? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("ratingCount")
	val ratingCount: Any? = null
)

data class UsDetails(

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("unitShort")
	val unitShort: String? = null,

	@field:SerializedName("unitLong")
	val unitLong: String? = null
)
