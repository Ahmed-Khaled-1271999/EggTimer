package com.example.egg_timer;

public class Timer {
    private int minutes;
    private int seconds;

    public Timer(){

    }
    public Timer(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
