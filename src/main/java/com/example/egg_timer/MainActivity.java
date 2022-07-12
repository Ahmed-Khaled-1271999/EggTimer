package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Timer timer=new Timer(0,10);

    TextView timerTextView;
    SeekBar minuteSliderSeekBar;
    Button goButton;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView=(TextView)findViewById(R.id.timerTextView);

        minuteSliderSeekBar=(SeekBar)findViewById(R.id.minuteSliderSeekBar);
        minuteSliderSeekBar.setMax(25*60);
        minuteSliderSeekBar.setProgress(10);
        minuteSliderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { //i now represent a second not ms
                UpdateTime(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        goButton=(Button)findViewById(R.id.goButton);
    }

    boolean counterActive=false;
    public void go(View view){
        if(counterActive){
            stopTimer();
        }
        else { //at first press the bunch after else always will implemented
            timerTextView.setText(timerTextView.getText().toString());
            counterActive = true;
            minuteSliderSeekBar.setEnabled(false);
            goButton.setText("Stop");               //minuteSliderSeekBar.getProgress()*1000)+ 100
            countDownTimer = new CountDownTimer((timer.getMinutes()*60+timer.getSeconds())*1000+100 , 1 * 1000) { // +100 ms for error ratio
                @Override
                public void onTick(long l) { // l is the No. of milli seconds left also
                    UpdateTime((int) l / 1000);
                }
                @Override
                public void onFinish() {
                    // IMPORTANT when you are inside inner Context and can not use this key word Use getApplicationContext()
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                    mediaPlayer.start();

                    resetTimer();
                }
            }.start();
        }
    }

    public void UpdateTime(int SecondsLeft){
        timer.setMinutes(SecondsLeft/60);
        timer.setSeconds(SecondsLeft-(timer.getMinutes()*60));
        String secondsString00=Integer.toString(timer.getSeconds());
        if(timer.getSeconds()<10)
            secondsString00="0"+secondsString00;
        timerTextView.setText(Integer.toString(timer.getMinutes())+":"+secondsString00);
    }
    public void stopTimer(){
        timerTextView.setText(timerTextView.getText().toString());
        minuteSliderSeekBar.setEnabled(true);
        minuteSliderSeekBar.setProgress(minuteSliderSeekBar.getProgress());
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterActive=false;
    }
    public void resetTimer(){
        timerTextView.setText("0:10");
        minuteSliderSeekBar.setEnabled(true);
        minuteSliderSeekBar.setProgress(10);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterActive=false;
    }
}