package com.example.cookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.MealItemBinding
import com.example.cookingapp.pojo.MealByCategory

class CategoryMealsAdapter(private val onItemClicked: (MealByCategory)->Unit):RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>() {
    private var mealsList = ArrayList<MealByCategory>()
    @SuppressLint("NotifyDataSetChanged")
    fun setMealsList(mealsList: List<MealByCategory>){
        this.mealsList = mealsList as ArrayList<MealByCategory>
        notifyDataSetChanged()

    }
    inner class CategoryMealsViewModel(val binding:MealItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
        return CategoryMealsViewModel(MealItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        val meal = mealsList[position]
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.mealImg)
        holder.binding.mealName.text = mealsList[position].strMeal
        holder.itemView.setOnClickListener {
            onItemClicked(meal)
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }


}


