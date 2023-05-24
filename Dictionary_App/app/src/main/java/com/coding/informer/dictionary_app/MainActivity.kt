package com.coding.informer.dictionary_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    var apiResponseView: TextView? = null;
    var mRequestQueue : RequestQueue? = null;
    var mStringRequest : StringRequest? = null;
    var searchWordTextInput : TextInputEditText? = null;
    var searchWord : String? = "grain";
    var searchButton : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        apiResponseView = findViewById<TextView>(R.id.apiResponseText)

        searchWordTextInput = findViewById<TextInputEditText>(R.id.searchWordTextInput)

        searchButton = findViewById<Button>(R.id.searchButton)



//        searchWordTextInput!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable) {
//                searchWord = s.toString();
//            }
//        })

        searchButton!!.setOnClickListener {
            callDictionaryAPI();
        }

//        searchButton!!.setOnClickListener {
//            callDictionaryAPI();
//        }


//        errorEditText!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable) {
//                if (s.length > errorInputLayout!!.counterMaxLength) errorInputLayout!!.error =
//                    "Max character length is " + errorInputLayout!!.counterMaxLength else errorInputLayout!!.error =
//                    null
//            }
//        })
//        customErrorEditText!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable) {
//                if (s.length > customErrorInputLayout!!.counterMaxLength) customErrorInputLayout!!.error =
//                    "Max character length is " + customErrorInputLayout!!.counterMaxLength else customErrorInputLayout!!.error =
//                    null
//            }
//        })
//        getRandomDogs();
    }

    private fun callDictionaryAPI() {
        mRequestQueue = Volley.newRequestQueue(this)

        mStringRequest = StringRequest(
            Request.Method.GET, Api.DICTIONARY_BASE_URL + "/" + searchWord,
            { response ->
//                apiResponseView = findViewById<View>(R.id.apiResponseText) as TextView?
//                apiResponseView?.text = response;
//                apiResponseView?.setTextColor(R.color.colorPrimary.red)
                Log.d("API Response", response)
                Toast.makeText(applicationContext, "API Response :$response", Toast.LENGTH_LONG)
                    .show() //display the response on screen

            }
        ) { error ->
//            Log.d(TAG, "Error :$error")
            Toast.makeText(applicationContext, "Ran into error during API Request", Toast.LENGTH_LONG)
                .show()
        }
        mRequestQueue!!.add(mStringRequest)
    }

//    private fun getRandomDogs() {
//        // RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this)
//
//        // String Request initialized
//        mStringRequest = StringRequest(
//            Request.Method.GET, Api.BASE_URL,
//            { response ->
//                apiResponseView = findViewById<View>(R.id.apiResponseText) as TextView?
//                apiResponseView?.text = response;
////                apiResponseView?.setTextColor(R.color.colorPrimary.red)
//                Log.d("API Response", response)
//                Toast.makeText(applicationContext, "API Response :$response", Toast.LENGTH_LONG)
//                    .show() //display the response on screen
//
//            }
//        ) { error -> Log.i(TAG, "Error :$error") }
//        mRequestQueue!!.add(mStringRequest)
//    }

//    private fun getRandomDogs(){
//        val call: Call<List<Results>> = RetrofitClient.getInstance().myApi.getDogs();
//        call.enqueue(object : Callback<List<Results?>?> {
//            override fun onResponse(call: Call<List<Results?>?>?, response: Response<List<Results?>?>) {
//                Log.d("API RESPONSE", response.toString());
//                apiResponseListView?.setText("API Response: \n" + response.toString());
//
//            }
//
//            override fun onFailure(call: Call<List<Results?>?>?, t: Throwable?) {
//                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}
