package com.coding.informer.radioplayerapp.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.coding.informer.radioplayerapp.C;
import com.coding.informer.radioplayerapp.service.FMService;
import com.coding.informer.radioplayerapp.service.RadioStateUpdater;

public class RadioController {

    private final Context mContext;
    private final RadioState mState;
    private BroadcastReceiver mTunerStateUpdater;

    public RadioController(Context mContext) {
        this.mContext = mContext;
        mState = new RadioState();
    }

    public void requestForCurrentState(@Nullable RadioStateUpdater.TunerStateListener callback){
        getCurrentState(state -> {
            mState.setStatus(state.getStatus());
            mState.setFrequency(state.getFrequency());
            mState.setStereo(state.isStereo());
            mState.setPs(state.getPs());
            mState.setRecording(state.isRecording());
            mState.setRecordingStarted(state.getRecordingStarted());

            if(callback != null){
                final int mode =
                        RadioStateUpdater.SET_STATUS |
                        RadioStateUpdater.SET_FREQUENCY |
                        RadioStateUpdater.SET_INITIAL |
                        RadioStateUpdater.SET_RECORDING;
                callback.onStateUpdated(mState, mode);
            }
        });
    }

    private void send(final String action){
        send(action, new Bundle());
    }

    private void send(final String action, final Bundle bundle){
        mContext.startService(new Intent(mContext, FMService.class).setAction(action).putExtras(bundle));
    }

    public interface CurrentStateListener {
        void onCurrentStateReady(final RadioState state);
    }

    public void getCurrentState(final CurrentStateListener listener){
        mContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                listener.onCurrentStateReady(intent.getParcelableExtra(C.Key.STATE));
                mContext.unregisterReceiver(this);
            }
        }, new IntentFilter(C.Event.CURRENT_STATE));

        send(C.Command.REQUEST_CURRENT_STATE);
    }













































}
