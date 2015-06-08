package com.luisgoyes.doggerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private final boolean[] opcionPrincipal = {false, true, true};
    private final boolean[] opcionMapa = {true, false, true};
    private boolean[] opcion = opcionPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.iPrincipal).setVisible(opcion[0]);
        menu.findItem(R.id.iMap).setVisible(opcion[1]);
        menu.findItem(R.id.iAbout).setVisible(opcion[2]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.iPrincipal:
                opcion = opcionPrincipal;

                break;
            case R.id.iMap:
                opcion = opcionMapa;
                break;
            case R.id.iAbout:
                break;
        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }
}
