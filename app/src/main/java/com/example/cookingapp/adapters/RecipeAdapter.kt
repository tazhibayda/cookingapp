package com.example.cookingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.R
import com.example.cookingapp.models.RecipeModel

class RecipeAdapter(private val context: Context, courseModelArrayList: ArrayList<RecipeModel>) :
    RecyclerView.Adapter<RecipeAdapter.Viewholder>() {
    private val courseModelArrayList: ArrayList<RecipeModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.Viewholder {
        // to inflate the layout for each item of recycler view.
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_for_recipes, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.Viewholder, position: Int) {
        // to set data to textview and imageview of each card layout
        val model: RecipeModel = courseModelArrayList[position]
        holder.recipe_name.setText(model.getName())
        holder.recipe_time.setText("" + model.getTime())
        holder.recipe_ppl.setText("" + model.getPpl())
        holder.recipe_text.setText("" + model.getText())
        holder.recipe_image.setImageResource(model.getImage())
    }

    override fun getItemCount(): Int {
        // this method is used for showing number of card items in recycler view.
        return courseModelArrayList.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipe_image: ImageView
        val recipe_name: TextView
        val recipe_text: TextView
        val recipe_time: TextView
        val recipe_ppl: TextView
        init {
            recipe_image = itemView.findViewById(R.id.recipe_image)
            recipe_name = itemView.findViewById(R.id.recipe_name)
            recipe_text = itemView.findViewById(R.id.recipe_text)
            recipe_time = itemView.findViewById(R.id.recipe_time)
            recipe_ppl = itemView.findViewById(R.id.recipe_ppl)
        }
    }

    // Constructor
    init {
        this.courseModelArrayList = courseModelArrayList
    }
}