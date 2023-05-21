package com.coding.informer.dictionary_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


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
    }
}
