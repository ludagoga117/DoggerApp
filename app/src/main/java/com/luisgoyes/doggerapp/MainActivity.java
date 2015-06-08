package com.luisgoyes.doggerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private final boolean[] opcionPrincipal = {false, false, false, false, true, true};
    private final boolean[] opcionMapa = {true, true, true, true, false, true};
    private boolean[] opcion = opcionPrincipal;
    //private static BaseDeDatos dataBase = new BaseDeDatos();

    private static String dogger_marker_tag = ((Integer)(R.mipmap.ic_dogger_marker)).toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        PagerHolder f1 = new PagerHolder();
        getFragmentManager().beginTransaction().add(android.R.id.content, f1).commit();
        opcion = opcionPrincipal;
/*
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
*/
    }

    //public static BaseDeDatos getMasterDataBase(){
    //    return dataBase;
    //}

    public static String getDogger_marker_tag(){
        return dogger_marker_tag;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(opcion[0]==false){
            PrincipalMenuItem();
        }else if(opcion[4]==false){
            MapaMenuItem();
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
        menu.findItem(R.id.iAbout).setVisible(opcion[5]);
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
            case R.id.iAbout:
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
                opcion[5]=true;
                break;
            case R.id.iAdd:
            case R.id.iEdit:
            case R.id.iRemove:
            case R.id.iMap:
                opcion[0]=true;
                opcion[1]=true;
                //if(dataBase.isEmpty()==true) {
                    opcion[2] = false;
                    opcion[3] = false;
                //}else{
                    opcion[2]=true;
                    opcion[3]=true;
                //}
                opcion[4]=false;
                opcion[5]=true;
                break;
        }
        invalidateOptionsMenu();
    }

    private void PrincipalMenuItem(){
        PagerHolder f2 = new PagerHolder();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f2).commit();
    }

    private void MapaMenuItem(){
        F_mapa f3 = new F_mapa();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f3).commit();
    }

    private void AddMenuItem(){
        //dataBase.add(53.551,9.993,"Kiel","Kiel is cool",dogger_marker_tag);
        //dataBase.add(53.558, 9.927, "Hamburg", null, dogger_marker_tag);
    }

    private void EditMenuItem() {

    }

    private void RemoveMenuItem(){

    }
}
