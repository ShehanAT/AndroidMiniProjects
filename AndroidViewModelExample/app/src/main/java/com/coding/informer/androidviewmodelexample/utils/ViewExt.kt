package com.coding.informer.androidviewmodelexample.utils

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar


class ViewExt {
}

fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.showSoftInput(this, 0)
}


fun View.hideKeyboard(){
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

/**
 * Transforms static Java function Snackbar.make() to an extension function on View
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int){
    Snackbar.make(this, snackbarText, timeLength).run {
        addCallback(object: Snackbar.Callback() {
            override fun onShown(sb: Snackbar?){
                EspressoIdlingResource.increment()
            }
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int){
                EspressoIdlingResource.decrement()
            }
        })
    }

}

fun View.showToast(
    lifecycleOwner: LifecycleOwner,
    ToastEvent: LiveData<SingleEvent<Any>>,
    timeLength: Int
) {
    ToastEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            when (it) {
                is String -> Toast.makeText(this.context, it, timeLength).show()
                is Int -> Toast.makeText(this.context, this.context.getString(it), timeLength)
                    .show()
                else -> {

                }
            }
        }
    })
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<SingleEvent<Any>>,
    timeLength: Int) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            when(it){
                is String -> {
                    hideKeyboard()
                    showSnackbar(it, timeLength)
                }
                is Int -> {
                    hideKeyboard()
                    showSnackbar(this.context.getString(it), timeLength)
                }
                else -> {

                }
            }
        }
    })
}