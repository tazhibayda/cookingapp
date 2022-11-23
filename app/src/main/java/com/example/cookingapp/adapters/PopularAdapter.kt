package com.example.cookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.PopularItemBinding
import com.example.cookingapp.pojo.MealByCategory
import com.example.cookingapp.pojo.MealsByCategoryList

class PopularAdapter(): RecyclerView.Adapter<PopularAdapter.PopularMealViewHolder>() {
    private var mealsList  = ArrayList<MealByCategory>()

    fun setMeals(mealsList: ArrayList<MealByCategory>, mealList: ArrayList<MealsByCategoryList>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {

        return PopularMealViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.popularItem)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
    class PopularMealViewHolder(val binding:PopularItemBinding):RecyclerView.ViewHolder(binding.root)
}