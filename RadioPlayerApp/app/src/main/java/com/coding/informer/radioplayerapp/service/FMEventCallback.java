package com.coding.informer.radioplayerapp.service;

import android.os.Build;
import android.os.Bundle;

public interface FMEventCallback {
    void onEvent(String event, Bundle bundle);
}
