package com.coding.informer.androidviewmodelexample.views.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.coding.informer.androidviewmodelexample.SPLASH_DELAY
import com.coding.informer.androidviewmodelexample.databinding.SplashActivityBinding
import com.coding.informer.androidviewmodelexample.views.base.BaseActivity
import com.coding.informer.androidviewmodelexample.views.component.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    private lateinit var binding: SplashActivityBinding

    override fun initViewBinding(){
        binding = SplashActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen(){
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }

}