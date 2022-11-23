package com.example.cookingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.HomePageActivity
import com.example.cookingapp.R
import com.example.cookingapp.adapters.FavouritesRecipesAdapter
import com.example.cookingapp.databinding.FragmentFavouriteBinding
import com.example.cookingapp.viewModel.InspirationViewModel
import com.google.android.material.snackbar.Snackbar

class FavouriteFragment : Fragment() {
    private lateinit var binding :FragmentFavouriteBinding
    private lateinit var viewModel: InspirationViewModel
    private lateinit var favouritesAdapter :FavouritesRecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as HomePageActivity).viewModel
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            binding = FragmentFavouriteBinding.inflate(inflater)
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFavourites()
        prepareRecyclerView()
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = true
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
                viewModel.deleteRecipe(favouritesAdapter.differ.currentList[position])

                Snackbar.make(requireView(),"Recipe deleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.insertRecipe(favouritesAdapter.differ.currentList[position])
                    }
                ).show()
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavourites)
    }

    private fun prepareRecyclerView() {
        favouritesAdapter = FavouritesRecipesAdapter()
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter= favouritesAdapter
        }
    }

    private fun observeFavourites() {
        viewModel.observeFavouritesRecipesLiveDara().observe(viewLifecycleOwner,{recipes->
            favouritesAdapter.differ.submitList(recipes)
        })
    }
}
