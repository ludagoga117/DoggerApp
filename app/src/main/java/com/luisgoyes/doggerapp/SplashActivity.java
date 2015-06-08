package com.luisgoyes.doggerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {

    private static final long SPLASH_DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title not the title bar

        setContentView(R.layout.activity_splash);
        TimerTask task = new TimerTask(){
            public void run(){
                Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,SPLASH_DELAY);
    }

}
