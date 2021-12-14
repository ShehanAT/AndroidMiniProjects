package com.coding.informer.androidviewmodelexample.views.component.expenses

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coding.informer.androidviewmodelexample.data.DataRepository
import com.coding.informer.androidviewmodelexample.data.DataRepositorySource
import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.dto.expenses.Expenses
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.models.Expense
import com.coding.informer.androidviewmodelexample.utils.SingleEvent
import com.coding.informer.androidviewmodelexample.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import com.coding.informer.androidviewmodelexample.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.InternalCoroutinesApi


/**
 * Created by AhmedEltaher
 */
@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val dataRepository: DataRepositorySource):
    BaseViewModel() {

    /**
     * Data -> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val expensesLiveDataPrivate = MutableLiveData<Resource<Expenses>>()
    val expensesLiveData: LiveData<Resource<Expenses>> get() = expensesLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val expenseSearchFoundPrivate: MutableLiveData<ExpensesItem> = MutableLiveData()
    val expenseSearchFound: LiveData<ExpensesItem> get() = expenseSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openExpenseDetailsPrivate = MutableLiveData<SingleEvent<ExpensesItem>>()
    val openExpenseDetails: LiveData<SingleEvent<ExpensesItem>> get() = openExpenseDetailsPrivate

    /**
     * Error handling as UI
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    @InternalCoroutinesApi
    fun getRecipes() {
        viewModelScope.launch {
            expensesLiveDataPrivate.value = Resource.Loading()
//            wrapEspressoIdlingResource {
//                dataRepository.requestExpenses().collect {
//                    expensesLiveDataPrivate.value = it
//                }
//            }
        }
    }

    fun openRecipeDetails(recipe: ExpensesItem) {
        openExpenseDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(expenseName: String) {
//        expensesLiveDataPrivate.value?.data?.expensesList?.let {
//            if (it.isNotEmpty()) {
//                for (expense in it) {
//                    if (expense.name.toLowerCase(Locale.ROOT).contains(expenseName.toLowerCase(Locale.ROOT))) {
//                        expenseSearchFoundPrivate.value = expense
//                        return
//                    }
//                }
//            }
//        }
        return noSearchFoundPrivate.postValue(Unit)
    }

}