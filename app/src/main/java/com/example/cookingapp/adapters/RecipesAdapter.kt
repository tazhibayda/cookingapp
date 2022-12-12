package com.example.cookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.MealItemBinding
import com.example.cookingapp.pojo.Meal

class RecipesAdapter :RecyclerView.Adapter<RecipesAdapter.FavouritesRecipeAdapterViewHolder>(){

    inner class FavouritesRecipeAdapterViewHolder(val binding: MealItemBinding) :RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavouritesRecipeAdapterViewHolder {
        return FavouritesRecipeAdapterViewHolder(MealItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: FavouritesRecipeAdapterViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        Glide.with(holder.itemView).load(recipe.strMealThumb).into(holder.binding.mealImg)
        holder.binding.mealName.text = recipe.strMeal
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}