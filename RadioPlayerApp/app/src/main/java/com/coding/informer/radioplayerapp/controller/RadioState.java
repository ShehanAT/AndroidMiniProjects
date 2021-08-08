package com.coding.informer.radioplayerapp.controller;

import android.os.Parcel;
import android.os.Parcelable;

public final class RadioState implements Parcelable {

    private TunerStatus status = TunerStatus.IDLE;

    private int frequency = 0;

    private int pi;

    private int pty;

    private String ps = "";

    private String rt = "";

    private int rssi;

    private boolean stereo;

    private boolean recording = false;

    private long recordingStarted = -1L;

    private boolean forceSpeaker = false;

    public RadioState(){

    }

    public static final Creator<RadioState> CREATOR = new Creator<RadioState>() {
        @Override
        public RadioState createFromParcel(Parcel in) {
            return new RadioState(in);
        }

        @Override
        public RadioState[] newArray(int size) {
            return new RadioState[size];
        }
    };

    public TunerStatus getStatus() {
        return status;
    }

    public void setStatus(TunerStatus status) {
        this.status = status;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getPi() {
        return pi;
    }

    public void setPi(int pi) {
        this.pi = pi;
    }

    public int getPty() {
        return pty;
    }

    public void setPty(int pty) {
        this.pty = pty;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public boolean isStereo() {
        return stereo;
    }

    public void setStereo(boolean stereo) {
        this.stereo = stereo;
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean recording) {
        this.recording = recording;
    }

    public long getRecordingStarted() {
        return recordingStarted;
    }

    public void setRecordingStarted(long recordingStarted) {
        this.recordingStarted = recordingStarted;
    }

    public boolean isForceSpeaker() {
        return forceSpeaker;
    }

    public void setForceSpeaker(boolean forceSpeaker) {
        this.forceSpeaker = forceSpeaker;
    }

    protected RadioState(final Parcel in)
    {
        status = (TunerStatus) in.readValue(TunerStatus.class.getClassLoader());
        frequency = in.readInt();
        pi = in.readInt();
        pty = in.readInt();
        ps = in.readString();
        rt = in.readString();
        rssi = in.readInt();
        stereo = in.readInt() > 0;
        recording = in.readInt() > 0;
        recordingStarted = in.readLong();
        forceSpeaker = in.readInt() > 0;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(frequency);
        parcel.writeInt(pi);
        parcel.writeInt(pty);
        parcel.writeString(ps);
        parcel.writeString(rt);
        parcel.writeInt(rssi);
        parcel.writeByte((byte) (stereo ? 1 : 0));
        parcel.writeByte((byte) (recording ? 1 : 0));
        parcel.writeLong(recordingStarted);
        parcel.writeByte((byte) (forceSpeaker ? 1 : 0));
    }

    @Override
    public String toString() {
        return "RadioState{" +
                "status=" + status +
                ", frequency=" + frequency +
                ", pi=" + pi +
                ", pty=" + pty +
                ", ps='" + ps + '\'' +
                ", rt='" + rt + '\'' +
                ", rssi=" + rssi +
                ", stereo=" + stereo +
                ", recording=" + recording +
                ", recordingStarted=" + recordingStarted +
                ", forceSpeaker=" + forceSpeaker +
                '}';
    }
}

