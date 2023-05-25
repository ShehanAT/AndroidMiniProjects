package com.coding.informer.dictionary_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject





class MainActivity : AppCompatActivity() {
    var apiResponseView: TextView? = null;
    var mRequestQueue : RequestQueue? = null;
    var mStringRequest : StringRequest? = null;
    var searchWordTextInput : TextInputEditText? = null;
    var searchWord : String? = "grain";
    var searchButton : Button? = null;
    var definitionList : ArrayList<String> = ArrayList();
    var definitionListStr : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        apiResponseView = findViewById<TextView>(R.id.apiResponseText)

        searchWordTextInput = findViewById<TextInputEditText>(R.id.searchWordTextInput)

        searchButton = findViewById<Button>(R.id.searchButton)

        searchWordTextInput!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                searchWord = s.toString();
            }
        })

        searchButton!!.setOnClickListener {
            callDictionaryAPI();
        }

    }

    private fun callDictionaryAPI() {
        mRequestQueue = Volley.newRequestQueue(this)

        mStringRequest = StringRequest(
            Request.Method.GET, Api.DICTIONARY_BASE_URL + "/" + searchWord,
            { response ->
                Toast.makeText(applicationContext, "Search word: " + searchWord, Toast.LENGTH_LONG)
                    .show()
                val jsonResponse = JSONArray(response);
//Syntax for traversing jsonResponse in order to extract definitions:
// (((jsonResponse.get(0) as JSONObject).getJSONArray("meanings").get(0) as JSONObject).getJSONArray("definitions").get(0) as JSONObject).getString("definition")
                val meaningsObj = (jsonResponse.get(0) as JSONObject).getJSONArray("meanings");
                try{
                    definitionListStr = "";
                    for (i  in 0 until meaningsObj.length()) {
                        val meaningsObj2 = (meaningsObj.get(i) as JSONObject).getJSONArray("definitions");
                        for ( j in 0 until meaningsObj2.length()) {
                            var defObj = (meaningsObj2.get(j) as JSONObject)
                            definitionListStr += "* " + (defObj.getString("definition")) + "\n";
                        }
                    }
                    Log.d("Definition List:", definitionList.toList().toString())
//                    val definitionListView = findViewById<RecyclerView>(R.id.definitionList)
                    apiResponseView = findViewById<View>(R.id.apiResponseText) as TextView?
                    apiResponseView?.text = definitionListStr;

                } catch (e : Exception) {
                    Log.d("API Response:", "Ran into error while parsing API Response")
                }

//                for (JSONObject defObj : (((jsonResponse.get(0) as JSONObject).getJSONArray("meanings").get(0) as JSONObject).getJSONArray("definitions").get(0) as JSONObject)) {
//
//                }


                Log.d("API Response", response)

            }
        ) { error ->
            Toast.makeText(applicationContext, "Ran into error during API Request", Toast.LENGTH_LONG)
                .show()
        }
        mRequestQueue!!.add(mStringRequest)
    }

}