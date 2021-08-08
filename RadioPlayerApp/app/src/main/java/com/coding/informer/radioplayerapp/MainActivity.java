package com.coding.informer.radioplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coding.informer.radioplayerapp.activity.RadioPlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButtonClick(View view){
        Intent radioIntent = new Intent(MainActivity.this, RadioPlayerActivity.class);
        startActivity(radioIntent);
    }
}