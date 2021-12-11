package com.coding.informer.androidloginregistrationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coding.informer.androidloginregistrationexample.activities.LoginActivity;
import com.coding.informer.androidloginregistrationexample.activities.RegisterActivity;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.coding.informer.androidloginregistrationexample.helpers.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
           }
        });

        DatabaseHelper.createDatabase(getApplicationContext());

    }
}