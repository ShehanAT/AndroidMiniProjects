package com.coding.informer.radioplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.coding.informer.radioplayerapp.activity.RadioPlayerActivity;
import com.coding.informer.radioplayerapp.view.MainRadioUIView;

public class MainActivity extends AppCompatActivity {
    private MainRadioUIView mRadioPlayerInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initUserInterface(){
        mRadioPlayerInfo = findViewById(R.id.frequency_info);

    }


    public void onButtonClick(View view){
        Intent radioIntent = new Intent(MainActivity.this, RadioPlayerActivity.class);
        startActivity(radioIntent);
    }

    public void onPlayButtonClick(View view){
        // Need to figure out how to play sample audio file on button click on frequency_info layout
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.sample_audio_1);
        mp.start();
    }
}