package com.coding.informer.androidviewmodelexample.views.component.details


import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coding.informer.androidviewmodelexample.data.DataRepositorySource
import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.utils.wrapEspressoIdlingResource
import com.coding.informer.androidviewmodelexample.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */
@HiltViewModel
open class DetailsViewModel @Inject constructor(private val dataRepository: DataRepositorySource) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipePrivate = MutableLiveData<ExpensesItem>()
    val recipeData: LiveData<ExpensesItem> get() = recipePrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val isFavouritePrivate = MutableLiveData<Resource<Boolean>>()
    val isFavourite: LiveData<Resource<Boolean>> get() = isFavouritePrivate

    fun initIntentData(recipe: ExpensesItem) {
        recipePrivate.value = recipe
    }

    open fun addToFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.expenseId?.let {
                    dataRepository.addToFavourite(it.toString()).collect { isAdded ->
                        isFavouritePrivate.value = isAdded
                    }
                }
            }
        }
    }

    fun removeFromFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.expenseId?.let {
                    dataRepository.removeFromFavourite(it.toString()).collect { isRemoved ->
                        when (isRemoved) {
                            is Resource.Success -> {
                                isRemoved.data?.let { isFavouritePrivate.value = Resource.Success(!isRemoved.data) }
                            }
                            is Resource.DataError -> {
                                isFavouritePrivate.value = isRemoved
                            }
                            is Resource.Loading -> {
                                isFavouritePrivate.value = isRemoved
                            }
                        }
                    }
                }
            }
        }
    }

    fun isFavourites() {
        viewModelScope.launch {
            isFavouritePrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                recipePrivate.value?.expenseId?.let {
                    dataRepository.isFavourite(it.toString()).collect { isFavourites ->
                        isFavouritePrivate.value = isFavourites
                    }
                }
            }
        }
    }
}
