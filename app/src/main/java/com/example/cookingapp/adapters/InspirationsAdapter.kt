package com.example.cookingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.models.InspirationsModel

class InspirationsAdapter(private val context: Context,inspirationList: ArrayList<InspirationsModel>):
    RecyclerView.Adapter<InspirationsAdapter.MyViewHolder>(){

    private val inspirationList :ArrayList<InspirationsModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view :View =LayoutInflater.from(parent.context).inflate(R.layout.layout_for_inspiration_item,
        parent,false)
        return MyViewHolder(view)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InspirationsAdapter.MyViewHolder, position: Int) {
        val model:InspirationsModel = inspirationList[position]
        holder.RecipesTitle.text =model.name
        holder.RecipesName.text =model.subname
        holder.recipe_time.text = model.time.toString()
        holder.recipe_ppl.text = model.ppl.toString()
        holder.dayImage.setImageResource(model.image)


    }

    override fun getItemCount(): Int {
        return inspirationList.size
    }

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val RecipesTitle :TextView
        val RecipesName :TextView
        val recipe_time:TextView
        val recipe_ppl :TextView
        val dayImage:ImageView
        init{
            RecipesTitle = itemView.findViewById(R.id.RecipesTitle)
            RecipesName = itemView.findViewById(R.id.RecipesName)
            recipe_time = itemView.findViewById(R.id.recipe_time)
            recipe_ppl = itemView.findViewById(R.id.recipe_ppl)
            dayImage = itemView.findViewById(R.id.dayImage)
        }
    }

    init {
        this.inspirationList = inspirationList
    }




}