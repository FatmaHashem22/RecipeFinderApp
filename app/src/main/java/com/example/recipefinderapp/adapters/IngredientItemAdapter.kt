package com.example.recipefinderapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipefinderapp.R
import com.example.recipefinderapp.model.ExtendedIngredientsItem
import com.example.recipefinderapp.model.ExtendedIngredientsItemDetails

class IngredientItemAdapter(var ingredients: List<ExtendedIngredientsItemDetails?>?) : RecyclerView.Adapter<IngredientItemAdapter.IngredientItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.details_ingredient_item,parent,false)
        return IngredientItemViewHolder(view)
    }

    override fun getItemCount(): Int = ingredients?.size!!

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) {
        val item = ingredients?.get(position)
        holder.quantity.text = item?.original
        holder.ingredient.text = item?.name
    }

    fun changeData(newList: List<ExtendedIngredientsItemDetails?>?) {
        ingredients = newList
        notifyDataSetChanged()

    }

    class IngredientItemViewHolder(itemView : View) : ViewHolder(itemView) {
        val quantity : TextView = itemView.findViewById(R.id.quantity)
        val ingredient : TextView = itemView.findViewById(R.id.bullet_ingredient)
    }

}