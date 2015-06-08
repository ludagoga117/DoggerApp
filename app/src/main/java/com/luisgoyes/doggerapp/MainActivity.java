package com.luisgoyes.doggerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private final boolean[] opcionPrincipal = {false, false, false, false, true, true, true};
    private boolean[] opcion = opcionPrincipal;
    private static BaseDeDatos dataBase = new BaseDeDatos();

    private static String dogger_marker_tag = ((Integer)(R.mipmap.ic_dogger_marker)).toString();

    private F_mapa f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        opcion = opcionPrincipal;

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

    }

    public static BaseDeDatos getMasterDataBase(){
        return dataBase;
    }

    public static String getDogger_marker_tag(){
        return dogger_marker_tag;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(opcion[0]==false){
            PrincipalMenuItem();
            actualizarMenu(R.id.iPrincipal);
        }else if(opcion[4]==false){
            MapaMenuItem();
            actualizarMenu(R.id.iMap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.iPrincipal).setVisible(opcion[0]);
        menu.findItem(R.id.iAdd).setVisible(opcion[1]);
        menu.findItem(R.id.iEdit).setVisible(opcion[2]);
        menu.findItem(R.id.iRemove).setVisible(opcion[3]);
        menu.findItem(R.id.iMap).setVisible(opcion[4]);
        menu.findItem(R.id.iRefresh).setVisible(opcion[5]);
        menu.findItem(R.id.iAbout).setVisible(opcion[6]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.iPrincipal:
                PrincipalMenuItem();
                break;
            case R.id.iAdd:
                AddMenuItem();
                break;
            case R.id.iEdit:
                EditMenuItem();
                break;
            case R.id.iRemove:
                RemoveMenuItem();
                break;
            case R.id.iMap:
                MapaMenuItem();
                break;
            case R.id.iRefresh:
                RefreshMenuItem();
                break;
            case R.id.iAbout:
                AboutMenuItem();
                break;
        }
        actualizarMenu(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void actualizarMenu(int itemId){
        switch(itemId){
            case R.id.iPrincipal:
                opcion[0]=false;
                opcion[1]=false;
                opcion[2]=false;
                opcion[3]=false;
                opcion[4]=true;
                opcion[5]=false;
                opcion[6]=true;
                break;
            case R.id.iAdd:
            case R.id.iEdit:
            case R.id.iRemove:
            case R.id.iMap:
                opcion[0]=true;
                opcion[1]=true;
                if(dataBase.isEmpty()==true) {
                    opcion[2] = false;
                    opcion[3] = false;
                    opcion[5] = false;
                }else{
                    opcion[2]=true;
                    opcion[3]=true;
                    opcion[5]=true;
                }
                opcion[4]=false;
                opcion[6]=true;
                break;
        }
        invalidateOptionsMenu();
    }

    private void PrincipalMenuItem(){
        if(f2!=null) {
            getFragmentManager().beginTransaction().remove(f2).commit();
        }
    }

    private void MapaMenuItem(){
        f2 = new F_mapa();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f2).commit();

    }

    private void AddMenuItem() {
        dataBase.add(53.551, 9.993, "Kiel", "Kiel is cool",dogger_marker_tag);
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void EditMenuItem() {
        dataBase.add(53.558, 9.927, "Hamburg",null,dogger_marker_tag);
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void RemoveMenuItem(){
        dataBase.remove(0);
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void RefreshMenuItem(){
        f2.updateMapMarkers();
    }

    private void AboutMenuItem(){
    }
}
