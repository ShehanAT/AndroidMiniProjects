package com.coding.informer.androidviewmodelexample.views.component.expenses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.databinding.ExpenseItemBinding
import com.coding.informer.androidviewmodelexample.views.base.listeners.RecyclerItemListener
import com.coding.informer.androidviewmodelexample.views.component.expenses.ExpensesListViewModel

class ExpensesAdapter(private val expensesListViewModel: ExpensesListViewModel, private val expenses: List<ExpensesItem>): RecyclerView.Adapter<ExpenseViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(expense: ExpensesItem){
            TODO("MIGHT NEED TO EDIT LATER")
            expensesListViewModel.openExpenseDetails
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemBinding = ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int){
        holder.bind(expenses[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}