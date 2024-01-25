package com.example.recipefinderapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipefinderapp.R
import com.example.recipefinderapp.model.Recipe
import com.example.recipefinderapp.model.RecipesItem

class SearchRecipesAdapter(var recipes : List<Recipe?>) : RecyclerView.Adapter<SearchRecipesAdapter.SearchViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_item,parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var item =recipes.get(position)
        holder.title.text =item?.title
//        holder.servings.text = item?.servings.toString() + " persons"
//        holder.prepTime.text = item?.preparationMinutes.toString() + " mins"
        holder.likes.text = item?.likes.toString()
        Glide.with(holder.itemView)
            .load(item?.image)
            .centerCrop()
            .into(holder.image)
    }

    fun changeData(newList: List<Recipe?>) {
        recipes = newList
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.image_dish)
        val title : TextView = itemView.findViewById(R.id.dish_name)
//        val servings : TextView = itemView.findViewById(R.id.servings)
//        val prepTime : TextView = itemView.findViewById(R.id.preparing_time)
        val likes : TextView = itemView.findViewById(R.id.likes_count)
    }
}