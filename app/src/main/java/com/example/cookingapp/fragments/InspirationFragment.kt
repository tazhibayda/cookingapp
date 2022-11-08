package com.example.cookingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.FragmentInspirationsBinding
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.pojo.ReceipFoodList
import com.example.cookingapp.retrofit.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InspirationFragment : Fragment() {

    private lateinit var binding :FragmentInspirationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInspirationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    retrofitInstance.api.getRandomReceip().enqueue(object :Callback<ReceipFoodList>{
        override fun onResponse(call: Call<ReceipFoodList>, response: Response<ReceipFoodList>) {
            if(response.body() !=null){
                val randomreceip :Meal =response.body()!!.meals[0]
                Glide.with(this@InspirationFragment)
                    .load(randomreceip.strMealThumb)
                    .into(binding.imgRandomReceip)
            }
            else{
                return
            }
        }

        override fun onFailure(call: Call<ReceipFoodList>, t: Throwable) {
            Log.d("InspirationFragment",t.message.toString())
        }
    })
    }

}

