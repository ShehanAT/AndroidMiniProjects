package com.coding.informer.radioplayerapp.interfaces;

public interface FMListener {

    int LISTEN_RDSSTATION_CHANGED = 0x0010010;

    int LISTEN_PS_CHANGED = 0x00100011;

    int LISTEN_RT_CHANGED = 0x00100100;

    int LISTEN_RECORDSTATE_CHANGED = 0x00100101;

    int LISTEN_RECORDERROR = 0x00100110;

    int LISTEN_RECORDMODE_CHANGED = 0x00100111;

    int LISTEN_SPEAKER_MODE_CHANGED = 0x00101000;

    String SWITCH_ANTENNA_VALUE = "switch_antenna_value";
    String CALLBACK_FLAG = "callback_flag";
    String KEY_IS_SWITCH_ANTENNA = "key_is_switch_antenna";
    String KEY_IS_TUNE = "key_is_tune";
    String KEY_TUNE_TO_STATION = "key_tune_to_station";
    String KEY_IS_SEEK = "key_is_seek";
    String KEY_SEEK_TO_STATION = "key_seek_to_station";
    String KEY_IS_SCAN = "key_is_scan";
    String KEY_RDS_STATION = "key_rds_station";
    String KEY_PS_INFO = "key_ps_info";
    String KEY_RT_INFO = "key_rt_info";
    String KEY_STATION_NUM = "key_station_num";

    String KEY_AUDIOFOCUS_CHANGED = "key_audiofocus_changed";

    String KEY_RECORDING_STATE = "key_is_recording_state";
    String KEY_RECORDING_ERROR_TYPE = "key_recording_error_type";
    String KEY_IS_RECORDING_MODE = "key_is_recording_mode";
    
    String KEY_IS_SPEAKER_MODE = "key_is_speaker_mode";

    // Message to handle
    int MSGID_UPDATE_RDS = 1;
    int MSGID_UPDATE_CURRENT_STATION = 2;
    int MSGID_ANTENNA_UNAVAILABLE = 3;
    int MSGID_SWITCH_ANTENNA = 4;
    int MSGID_SET_RDS_FINISHED = 5;
    int MSGID_SET_CHANNEL_FINISHED = 6;
    int MSGID_FM_EXIT = 11;
    int MSGID_SCAN_FINISHED = 13;


 }
