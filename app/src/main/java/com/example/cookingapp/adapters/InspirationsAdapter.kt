package com.example.cookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingapp.R
import com.example.cookingapp.pojo.MealsByCategory


class InspirationsAdapter(): RecyclerView.Adapter<InspirationsAdapter.MyViewHolder>(){

    private var mealsList = ArrayList<MealsByCategory>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMeals(mealList: ArrayList<MealsByCategory>){
        this.mealsList = mealList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.popular_receips, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.item)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }



    class MyViewHolder(var binding: View) :RecyclerView.ViewHolder(binding.root) {
    }

}
