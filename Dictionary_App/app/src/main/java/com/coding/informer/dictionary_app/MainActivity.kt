package com.coding.informer.dictionary_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import okhttp3.OkHttpClient
import com.androidnetworking.error.ANError

import org.json.JSONArray

import com.androidnetworking.interfaces.JSONArrayRequestListener
import android.widget.Toast

import android.widget.ArrayAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var errorInputLayout: TextInputLayout? = null
    var customErrorInputLayout: TextInputLayout? = null
    var errorEditText: TextInputEditText? = null
    var customErrorEditText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        errorEditText = findViewById<View>(R.id.errorEditText) as TextInputEditText
        errorInputLayout = findViewById<View>(R.id.errorInputLayout) as TextInputLayout
        customErrorEditText = findViewById<View>(R.id.customErrorEditText) as TextInputEditText
        customErrorInputLayout = findViewById<View>(R.id.customErrorInputLayout) as TextInputLayout
        errorEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.length > errorInputLayout!!.counterMaxLength) errorInputLayout!!.error =
                    "Max character length is " + errorInputLayout!!.counterMaxLength else errorInputLayout!!.error =
                    null
            }
        })
        customErrorEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.length > customErrorInputLayout!!.counterMaxLength) customErrorInputLayout!!.error =
                    "Max character length is " + customErrorInputLayout!!.counterMaxLength else customErrorInputLayout!!.error =
                    null
            }
        })

        getRandomDogs();
    }

    private fun getRandomDogs(){
        val call: Call<List<Results>> = RetrofitClient.getInstance().myApi.getDogs();
        call.enqueue(object : Callback<List<Results?>?> {
            override fun onResponse(call: Call<List<Results?>?>?, response: Response<List<Results?>?>) {
                System.out.println(response.toString());
//                val myheroList: List<Results> = response.body()
//                val oneHeroes = arrayOfNulls<String>(myheroList.size)
//                for (i in myheroList.indices) {
//                    oneHeroes[i] = myheroList[i].name
//                }
//                superListView.setAdapter(
//                    ArrayAdapter(
//                        applicationContext,
//                        android.R.layout.simple_list_item_1,
//                        oneHeroes
//                    )
//                )
            }

            override fun onFailure(call: Call<List<Results?>?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<Results?>?>) {

}
