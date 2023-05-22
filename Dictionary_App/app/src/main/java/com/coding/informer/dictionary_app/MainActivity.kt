package com.coding.informer.dictionary_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import android.widget.ListView
import android.widget.TextView
import androidx.core.graphics.red
import retrofit2.Call
import retrofit2.Callback

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


class MainActivity : AppCompatActivity() {
    var errorInputLayout: TextInputLayout? = null
    var customErrorInputLayout: TextInputLayout? = null
    var errorEditText: TextInputEditText? = null
    var customErrorEditText: TextInputEditText? = null
    var apiResponseView: TextView? = null;
    var mRequestQueue : RequestQueue? = null;
    var mStringRequest : StringRequest? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiResponseView = findViewById<View>(R.id.apiResponseText) as TextView?

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

    private fun getRandomDogs() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this)

        // String Request initialized
        mStringRequest = StringRequest(
            Request.Method.GET, Api.BASE_URL,
            { response ->
                apiResponseView = findViewById<View>(R.id.apiResponseText) as TextView?
                apiResponseView?.text = response;
//                apiResponseView?.setTextColor(R.color.colorPrimary.red)
                Log.d("API Response", response)
                Toast.makeText(applicationContext, "API Response :$response", Toast.LENGTH_LONG)
                    .show() //display the response on screen

            }
        ) { error -> Log.i(TAG, "Error :$error") }
        mRequestQueue!!.add(mStringRequest)
    }

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

private fun <T> Call<T>.enqueue(callback: Callback<List<Results?>?>) {

}
