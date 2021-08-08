package com.coding.informer.radioplayerapp;

final public class C {
    public static final class Event {
        public static final String BASE = BuildConfig.APPLICATION_ID + ".action.EVT_";
        public static final String PREPARING = BASE + "PREPARING";
        public static final String INSTALLING = BASE + "INSTALLING";
        public static final String INSTALLED = BASE + "INSTALLED";
        public static final String LAUNCHING = BASE + "LAUNCHING";
        public static final String LAUNCHED = BASE + "LAUNCHED";
        public static final String LAUNCH_FAILED = BASE + "LAUNCH_FAILED";
        public static final String ENABLING = BASE + "ENABLING";
        public static final String ENABLED = BASE + "ENABLED";
        public static final String DISABLING = BASE + "DISABLING";
        public static final String DISABLED = BASE + "DISABLED";
        public static final String FREQUENCY_SET = BASE + "FREQUENCY_SET";
        public static final String UPDATE_RSSI = BASE + "UPDATE_RSSI";
        public static final String UPDATE_PS = BASE + "UPDATE_PS";
        public static final String UPDATE_PTY = BASE + "UPDATE_PTY";
        public static final String UPDATE_PI = BASE + "UPDATE_PI";
        public static final String UPDATE_RT = BASE + "UPDATE_RT";
        public static final String UPDATE_AF = BASE + "UPDATE_AF";
        public static final String UPDATE_STEREO = BASE + "UPDATE_STEREO";
        public static final String HW_SEARCH_DONE = BASE + "HW_SEARCH_DONE";
        public static final String JUMP_COMPLETE = BASE + "JUMP_COMPLETE";
        public static final String HW_SEEK_COMPLETE = BASE + "HW_SEEK_COMPLETE";


        public static final String RECORD_STARTED = BASE + "RECORD_STARTED";
        public static final String RECORD_TIME_UPDATED = BASE + "RECORD_TIME_UPDATED";
        public static final String RECORD_ENDED = BASE + "RECORD_ENDED";
        public static final String CHANGE_SPEAKER_MODE = BASE + "CHANGE_SPEAKER_MODE";
        public static final String FAVORITE_LIST_CHANGED = BASE + "FAVORITE_LIST_CHANGED";
        public static final String CURRENT_STATE = BASE + "CURRENT_STATE";
        public static final String KILLED = BASE + "KILLED";
    }

    public static final class Key {
        public static final String IS_SPEAKER = "is_speaker";
        public static final String STEREO_MODE = "stereo_mode";
        public static final String PS = "PS";
        public static final String RT = "RT";
        public static final String PTY = "PTY";
        public static final String PI = "PI";
        public static final String RSSI = "RSSI";
        public static final String FREQUENCY = "FREQUENCY";
        public static final String STATE = "STATE";
    }

    public static final class PrefKey {
        public static final String TUNER_REGION = "tuner_region";
        public static final String TUNER_SPACING = "tuner_spacing";
    }

    public static final class PrefDefaultValue {
        public static final int TUNER_REGION = 1;
        public static final int TUNER_SPACING = 2;
    }

    public static final class Command {
        public static final String REQUEST_CURRENT_STATE = "ui_loaded";
    }
}
