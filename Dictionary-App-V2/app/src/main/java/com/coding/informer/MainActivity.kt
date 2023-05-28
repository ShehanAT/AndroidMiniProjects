package com.coding.informer

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.google.android.material.textfield.TextInputEditText
import informer.R
//import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    var apiResponseView: TextView? = null;
    var mRequestQueue : RequestQueue? = null;
    var mStringRequest : StringRequest? = null;
    var searchWordTextInput : TextInputEditText? = null;
    var searchWord : String? = "grain";
    var searchButton : Button? = null;
    var definitionList : ArrayList<String> = ArrayList();
    var definitionListStr : String = "";
    var pronunciationBtn : Button? = null;

    companion object {
        private const val REQUEST_CODE_STT = 1
    }

    private val textToSpeechEngine: TextToSpeech by lazy {
        TextToSpeech(this,
            TextToSpeech.OnInitListener { status ->
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechEngine.language = Locale.UK
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiResponseView = findViewById<TextView>(R.id.apiResponseText)

        searchWordTextInput = findViewById<TextInputEditText>(R.id.searchWordTextInput)

        pronunciationBtn = findViewById<Button>(R.id.pronunciationBtn)

        searchButton = findViewById<Button>(R.id.searchButton)

        searchButton!!.setOnClickListener {
            callDictionaryAPI();
        }

        searchWordTextInput!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                searchWord = s.toString();
            }
        })

        pronunciationBtn?.setOnClickListener {
            val text = searchWordTextInput!!.text.toString().trim()
            if (text.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
                } else {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }
            } else {
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_STT -> {
                if (resultCode == RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    result?.let {
                        val recognizedText = it[0]
                        searchWordTextInput?.setText(recognizedText)
                    }
                }
            }
        }
    }

    private fun callDictionaryAPI() {
        mRequestQueue = Volley.newRequestQueue(this)
        Log.d("API Response", "Sending response")
        mStringRequest = StringRequest(
            Request.Method.GET, Api.DICTIONARY_BASE_URL + "/" + searchWord,
            { response ->
                Log.d("API Response", "Received response")
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

    override fun onPause() {
        textToSpeechEngine.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeechEngine.shutdown()
        super.onDestroy()
    }
}
