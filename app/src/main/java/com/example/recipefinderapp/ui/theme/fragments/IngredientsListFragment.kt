package com.example.recipefinderapp.ui.theme.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.R
import com.example.recipefinderapp.adapters.IngredientsListAdapter


class IngredientsListFragment : Fragment() {

    lateinit var itemRecyclerView : RecyclerView
    lateinit var itemAdapter : IngredientsListAdapter
    lateinit var doneButton : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_ingredients_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemRecyclerView = view.findViewById(R.id.ingredient_items_recycler_view)
        val ingredientsList = resources.getStringArray(R.array.ingredient_list)
        itemAdapter = IngredientsListAdapter(ingredientsList)
        itemRecyclerView.adapter = itemAdapter
        doneButton = view.findViewById(R.id.done_button)


        doneButton.setOnClickListener {
            val selectedIngredients = itemAdapter.getSelectedIngredients().joinToString(",")
            Log.e("Selected Ingredients: ", "$selectedIngredients")
            val bundle = Bundle()
            bundle.putString("searchQuery", selectedIngredients)
            val nextFragment = SearchResultsFragment() // Create the fragment with arguments
            nextFragment.arguments = bundle

            val fragmentManager = activity?.supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()

            transaction?.replace(R.id.search_container_fragment,nextFragment)
            transaction?.commit()
        }



    }

}