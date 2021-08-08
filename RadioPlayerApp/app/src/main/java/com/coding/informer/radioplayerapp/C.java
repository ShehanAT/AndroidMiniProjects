package com.coding.informer.radioplayerapp;

final public class C {
    public static final class Event {
        private static final String BASE = BuildConfig.APPLICATION_ID + ".action.EVT_";


    }

    public static final class PrefKey {
        public static final String TUNER_REGION = "tuner_region";
        public static final String TUNER_SPACING = "tuner_spacing";
    }

    public static final class PrefDefaultValue {
        public static final int TUNER_REGION = 1;
        public static final int TUNER_SPACING = 2;

    }
}
