package com.coding.informer.radioplayerapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.coding.informer.radioplayerapp.controller.RadioController;
import com.coding.informer.radioplayerapp.controller.RadioState;

import net.grandcentrix.tray.AppPreferences;
import net.grandcentrix.tray.core.OnTrayPreferenceChangeListener;
import net.grandcentrix.tray.core.TrayItem;

import java.util.Collection;
import java.util.Map;
import java.util.Timer;

@SuppressWarnings({"deprecation", "unused"})
public class FMService extends Service implements FMEventCallback, OnTrayPreferenceChangeListener {

    private static final int NOTIFICATION_ID = 1027;
    private static final int NOTIFICATOIN_RECORD_ID = 1029;
    private static final String CHANNEL_ID = "default_channel";
    private static final String CHANNEL_RECORD_ID = "record_channel";

    private NotificationCompat.Builder mNBuilder;
    private NotificationManagerCompat mNotificationManager;

    private RadioController mRadioController;
    private FavoriteController mFavoriteController;

    private Map<Integer, String> mFavoriteList;

    private AbstractFMController mTunerDriver;
    private AudioService mAudioService;

    private BroadcastReceiver mEventReaction;
    private BroadcastReceiver mTunerStateUpdater;

    private AppPreferences mStorage;
    private Timer mTimer;
    private boolean mNeedRecreateNotification = true;
    private boolean mRecordingNow = false;

    private RadioState mState;













    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTrayPreferenceChanged(Collection<TrayItem> items) {

    }

    @Override
    public void onEvent(String event, Bundle bundle) {
        sendBroadcast(new Intent(event).putExtras(bundle));
    }
}
