package com.coding.informer.radioplayerapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;

import com.coding.informer.radioplayerapp.R;
import com.coding.informer.radioplayerapp.enums.PlaybackStatus;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import android.support.v4.media.session.MediaSessionCompat;



public class RadioService extends Service implements Player.EventListener, AudioManager.OnAudioFocusChangeListener {

    public static final String ACTION_PLAY = "com.coding.informer.ACTION_PLAY";
    public static final String ACTION_PAUSE = "com.coding.informer.ACTION_PAUSE";
    public static final String ACTION_STOP = "com.coding.informer.ACTION_STOP";

    private final IBinder iBinder = new LocalBinder();

    private Handler handler;
    private final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private SimpleExoPlayer exoPlayer;
    private MediaSessionCompat mediaSession;
    private MediaControllerCompat.TransportControls transportControls;

    private boolean onGoingCall = false;
    private TelephonyManager telephonyManager;

    private WifiManager.WifiLock wifiLock;

    private AudioManager audioManager;

    private String status;

    private String strAppName;
    private String strLiveBroadcast;
    private String streamUrl;

    @Override
    public void onCreate(){
        super.onCreate();

        strAppName = getResources().getString(R.string.app_name);
        strLiveBroadcast = getResources().getString(R.string.live_broadcast);

        onGoingCall = false;

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        notificationManager = new MediaNotificationManager(this);

        wifiLock = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, "mcScPAmpLocal");

        mediaSession = new MediaSessionCompat(this, getClass().getSimpleName());
        transportControls = mediaSession.getController().getTransportControls();
        mediaSession.setActive(true);
        mediaSession.setFlags(MediaSessionCompat.FLAGS_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setMetadata(new MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "...")
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, strAppName)
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, strLiveBroadcast)
            .build());
        mediaSession.setCallback(mediasSessionCallback);
    }

    public class LocalBinder extends Binder {
        public RadioService getService(){
            return RadioService.this;
        }
    }

    @Override
    public void onAudioFocusChange(int i) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    public class LocalBinder extends Binder {
        public RadioService getService(){
            return RadioService.this;
        }
    }

    private BroadcastReceiver becomingNoisyReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            pause();
        }
    };

    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_OFFHOOK
                    || state == TelephonyManager.CALL_STATE_RINGING) {
                if (!isPlaying()) return;
                onGoingCall = true;
                stop();
            } else if (state == Telephonymanager.CALL_STATE_IDLE) {
                if (!onGoingCall) return;

                onGoingCall = false;
                resume();
            }
        }
    }

    private MediaSessionCompat.Callback mediasSessionCallback = new MediaSessionCompat.Callback(){
        @Override
        public void onPause(){
            super.onPause();

            pause();
        }

        @Override
        public void onStop(){
            super.onStop();

            stop();

            notificationManager.cancelNotify();
        }

        @Override
        public void onPlay(){
            super.onPlay();

            resume();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return iBinder;
    }

    public void pause(){
        exoPlayer.setPlayWhenReady(false);

        audioManager.abandonAudioFocus(this);
        wifiLockRelease();
    }

    public void stop(){
        exoPlayer.stop();

        audioManager.abandonAudioFocus(this);
        wifiLockRelease();
    }

    public void playOrPause(String url){
        if(streamUrl != null && streamUrl.equals(url)){
            if(!isPlaying()){
                play(streamUrl);
            }else{
                pause();
            }
        }else{
            if(isPlaying()){
                pause();
            }
        }
    }

    public void play(String streamUrl){
        this.streamUrl = streamUrl;

        if(wifiLock != null && !wifiLock.isHeld()){
            wifiLock.acquire();
        }

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, getUserAgent(), BANDWIDTH_METER);

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .setExtractorsFactory(new DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(streamUrl));

        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    public boolean isPlaying(){
        return this.status.equals(PlaybackStatus.PLAYING);
    }

    private void wifiLockRelease(){
        if(wifiLock != null && wifiLocal.isHeld()){
            wifiLocal.release();
        }
    }

    private String getUserAgent(){
        return Util.getUserAgent(this, getClass().getSimpleName())
    }
}
