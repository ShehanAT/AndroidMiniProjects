package com.coding.informer.androidviewmodelexample.views.component.expenses

import androidx.activity.viewModels
import com.coding.informer.androidviewmodelexample.databinding.HomeActivityBinding
import com.coding.informer.androidviewmodelexample.views.base.BaseActivity
import com.coding.informer.androidviewmodelexample.views.component.expenses.adapter.ExpensesAdapter

class ExpensesListActivity: BaseActivity() {

    private lateinit var binding: HomeActivityBinding

    private val expensesListViewModel: ExpensesListViewModel by viewModels()
    private lateinit var expensesAdapter: ExpensesAdapter
    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun initViewBinding() {
        TODO("Not yet implemented")
    }


}