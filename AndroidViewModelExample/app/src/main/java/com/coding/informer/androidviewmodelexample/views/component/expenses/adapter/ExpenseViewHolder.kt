package com.coding.informer.androidviewmodelexample.views.component.expenses.adapter

import androidx.recyclerview.widget.RecyclerView
import com.coding.informer.androidviewmodelexample.R
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.databinding.ExpenseItemBinding
import com.coding.informer.androidviewmodelexample.views.base.listeners.RecyclerItemListener
import com.squareup.picasso.Picasso

/**
 * Created by AhmedEltaher
 */

class ExpenseViewHolder(private val itemBinding: ExpenseItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(expensesItem: ExpensesItem, recyclerItemListener: RecyclerItemListener){
        itemBinding.tvCaption.text = expensesItem.description
        itemBinding.tvName.text = expensesItem.title
        Picasso.get().load(expensesItem.title).placeholder(R.drawable.ic_money_icon).error(R.drawable.ic_money_icon).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(expensesItem) }
    }
}