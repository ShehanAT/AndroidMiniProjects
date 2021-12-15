package com.coding.informer.androidviewmodelexample.views.component.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.coding.informer.androidviewmodelexample.EXPENSE_ITEM_KEY
import com.coding.informer.androidviewmodelexample.R
import com.coding.informer.androidviewmodelexample.databinding.DetailsLayoutBinding
import com.coding.informer.androidviewmodelexample.views.base.BaseActivity
import com.squareup.picasso.Picasso

import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.utils.observe
import com.coding.informer.androidviewmodelexample.utils.toGone
import com.coding.informer.androidviewmodelexample.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AhmedEltaher
 */

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding
    private var menu: Menu? = null


    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.initIntentData(intent.getParcelableExtra(EXPENSE_ITEM_KEY) ?: RecipesItem())
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.details_menu, menu)
//        this.menu = menu
//        viewModel.isFavourites()
        return true
    }
//
//    fun onClickFavorite(mi: MenuItem) {
//        mi.isCheckable = false
//        if (viewModel.isFavourite.value?.data == true) {
//            viewModel.removeFromFavourites()
//        } else {
//            viewModel.addToFavourites()
//        }
//    }

    override fun observeViewModel() {
//        observe(viewModel.recipeData, ::initializeView)
//        observe(viewModel.isFavourite, ::handleIsFavourite)
    }
//
//    private fun handleIsFavourite(isFavourite: Resource<Boolean>) {
//        when (isFavourite) {
//            is Resource.Loading -> {
//                binding.pbLoading.toVisible()
//            }
//            is Resource.Success -> {
//                isFavourite.data?.let {
//                    handleIsFavouriteUI(it)
//                    menu?.findItem(R.id.add_to_favorite)?.isCheckable = true
//                    binding.pbLoading.toGone()
//                }
//            }
//            is Resource.DataError -> {
//                menu?.findItem(R.id.add_to_favorite)?.isCheckable = true
//                binding.pbLoading.toGone()
//            }
//        }
//    }

//    private fun handleIsFavouriteUI(isFavourite: Boolean) {
//        menu?.let {
//            it.findItem(R.id.add_to_favorite)?.icon =
//                    if (isFavourite) {
//                        ContextCompat.getDrawable(this, R.drawable.ic_star_24)
//                    } else {
//                        ContextCompat.getDrawable(this, R.drawable.ic_outline_star_border_24)
//                    }
//        }
//    }
//
//    private fun initializeView(recipesItem: RecipesItem) {
//        binding.tvName.text = recipesItem.name
//        binding.tvHeadline.text = recipesItem.headline
//        binding.tvDescription.text = recipesItem.description
//        Picasso.get().load(recipesItem.image).placeholder(R.drawable.ic_healthy_food_small)
//                .into(binding.ivRecipeImage)
//
//    }
}
