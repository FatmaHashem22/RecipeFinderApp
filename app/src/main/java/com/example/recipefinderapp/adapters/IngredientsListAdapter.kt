package com.example.recipefinderapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipefinderapp.R

class IngredientsListAdapter(val ingredients: Array<String?>) : Adapter<IngredientsListAdapter.IngredientsViewHolder>() {

    private val selectedIngredients = mutableListOf<String>()

    fun getSelectedIngredients() : List<String> = selectedIngredients.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ingredient_item,parent,false)
        return IngredientsViewHolder(view)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = ingredients[position]
        holder.itemView.setBackgroundResource(R.drawable.ingredient_item_selector)
        holder.name.text = item
        holder.itemView.setOnClickListener {

            if (holder.itemView.isSelected){
                selectedIngredients.remove(item!!)
            }
            else {
                selectedIngredients.add(item!!)
            }
            holder.itemView.isSelected = !holder.itemView.isSelected


        }
    }

    class IngredientsViewHolder(itemView : View) : ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.ingredient_name)
    }
}