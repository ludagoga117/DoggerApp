package com.luisgoyes.doggerapp;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends FragmentActivity {
    private int[] ImagesIDs = {R.drawable.im0, R.drawable.im1, R.drawable.im2, R.drawable.im3, R.drawable.im4, R.drawable.im5, R.drawable.im6, R.drawable.im7, R.drawable.im8, R.drawable.im9, R.drawable.im10 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.iAbout:

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
