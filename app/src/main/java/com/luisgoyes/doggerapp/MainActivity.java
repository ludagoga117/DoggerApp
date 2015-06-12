package com.luisgoyes.doggerapp;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.SQLDataException;


public class MainActivity extends ActionBarActivity {
    /*
    Algunas Ubicaciones Dogger
    Parque Norte Lat 6.271053 Lng -75.565714
    Planetario Lat 6.267811 Lng -75.565982
    Florida Lat 6.27084 Lng -75.577204
    */
    private final boolean[] opcionPrincipal = {false, false, false, false, true, true, true};
    private boolean[] opcion = opcionPrincipal;
    private static DataBaseManager dataBase;
    private static String dogger_marker_tag = ((Integer)(R.mipmap.ic_dogger_marker)).toString();
    public State s;
    public static FragmentManager fragmentManager;
    private F_mapa f2;
    private Add f3;
    private Remove f4;
    private Edit f5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBaseManager(this);
        fragmentManager=getFragmentManager();
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        s = State.HOME;
        opcion = opcionPrincipal;
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }

    public static DataBaseManager getMasterDataBase(){
        return dataBase;
    }

    public static String getDogger_marker_tag(){
        return dogger_marker_tag;
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch(s){
            case HOME:
                PrincipalMenuItem();
                actualizarMenu(R.id.iPrincipal);
                break;
            case ADD:
                break;
            case EDIT:
                break;
            case REMOVE:
                break;
            case MAP:
                MapaMenuItem();
                actualizarMenu(R.id.iMap);
                break;
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
            default:
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
                opcion[0] = true;
                opcion[1] = false;
                opcion[2] = false;
                opcion[3] = false;
                opcion[4] = true;
                opcion[5] = false;
                opcion[6] = true;
                break;
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
        s = State.HOME;
        if(f2!=null) {
            getFragmentManager().beginTransaction().remove(f2).commit();
        }
        if(f3!=null){
            getFragmentManager().beginTransaction().remove(f3).commit();
        }
    }

    private void MapaMenuItem(){
        s = State.MAP;
        f2 = new F_mapa();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f2).commit();

    }

    private void AddMenuItem() {
        s = State.ADD;
        f3 = new Add();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f3).commit();
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void EditMenuItem() {
        s = State.EDIT;
        f5 = new Edit();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f5).commit();
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void RemoveMenuItem(){
        s = State.REMOVE;
        f4 = new Remove();
        getFragmentManager().beginTransaction().replace(android.R.id.content, f4).commit();
        f2.clearMap();
        f2.updateMapMarkers();
    }

    private void RefreshMenuItem(){
        f2.updateMapMarkers();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.tRefresh), Toast.LENGTH_SHORT).show();
    }

    private void AboutMenuItem(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.sAbout))
                .setMessage(getResources().getString(R.string.sAboutInfo))
                .setNeutralButton(getResources().getString(R.string.sOk), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    public enum State {
        HOME, ADD, EDIT, REMOVE, MAP
    }

    @Override
    public void onBackPressed() {
        switch(s){
            case HOME:
                super.onBackPressed();
                break;
            case ADD:
            case EDIT:
            case REMOVE:
                MapaMenuItem();
                actualizarMenu(R.id.iMap);
                break;
            case MAP:
                PrincipalMenuItem();
                actualizarMenu(R.id.iPrincipal);
                break;
        }
    }

    public void addNew ( View view ){
        EditText etAddNome = (EditText) findViewById(R.id.AddNome);
        EditText etAddLat = (EditText) findViewById(R.id.AddLatitude);
        EditText etAddLong = (EditText) findViewById(R.id.AddLongitude);

        if( etAddNome.getText().toString().isEmpty() || etAddLat.getText().toString().isEmpty() || etAddLong.getText().toString().isEmpty()){
            if( etAddNome.getText().toString().isEmpty() ) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.tetAddNome),Toast.LENGTH_SHORT).show();
            }
            if( etAddLat.getText().toString().isEmpty() ) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.tetAddLat),Toast.LENGTH_SHORT).show();
            }
            if( etAddLong.getText().toString().isEmpty() ) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.tetAddLong),Toast.LENGTH_SHORT).show();
            }
        }else{
            if(dataBase.searchByName(etAddNome.getText().toString())||dataBase.searchByLocation(Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()))){
                if(dataBase.searchByName(etAddNome.getText().toString())){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.tsearchAddName),Toast.LENGTH_SHORT).show();
                }
                if(dataBase.searchByLocation(Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()))){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.tsearchAddLocation),Toast.LENGTH_SHORT).show();
                }
            }else {
                dataBase.add(Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()), etAddNome.getText().toString(), null, getDogger_marker_tag());
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.tAddSuccess),Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }

    public void addCancel ( View view ) {
        onBackPressed();
    }
}
