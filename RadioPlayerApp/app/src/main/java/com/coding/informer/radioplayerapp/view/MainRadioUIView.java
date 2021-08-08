package com.coding.informer.radioplayerapp.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.coding.informer.radioplayerapp.C;
import com.coding.informer.radioplayerapp.R;

import net.grandcentrix.tray.AppPreferences;

public class MainRadioUIView extends LinearLayout {
    private final AppPreferences mPreferences;
    private ImageButton playButton;
    public MainRadioUIView(Context context) {
        super(context);
        mPreferences = new AppPreferences(getContext());
        init();
    }

    public MainRadioUIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPreferences = new AppPreferences((getContext()));
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.frequency_info, this, true);

        playButton = findViewById(R.id.play_button);
//        playButton.setOnClickListener();
        reloadPreferences();
    }

    private void reloadPreferences(){
        final int regionPref = mPreferences.getInt(C.PrefKey.TUNER_REGION, C.PrefDefaultValue.TUNER_REGION);
        final int spacingPref = mPreferences.getInt(C.PrefKey.TUNER_SPACING, C.PrefDefaultValue.TUNER_SPACING);
    }

//    private void onPlayButtonClick(View view){
//        // Need to figure out how to play sample audio file on button click on frequency_info layout
//        MediaPlayer mp = MediaPlayer.create(getContext().getApplicationContext(), R.raw.sample_audio_1);
//        mp.start();
//    }
}
