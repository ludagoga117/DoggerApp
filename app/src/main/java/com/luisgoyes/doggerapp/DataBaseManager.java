package com.luisgoyes.doggerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
    public static final String TABLE_NAME ="franchises";
    public static final String FR_ID ="_id";
    public static final String FR_NAME ="name";
    public static final String FR_LAT ="latitude";
    public static final String FR_LNG ="longitude";

    public static final String CREATE_TABLE = " create table " +TABLE_NAME+ " ("
            + FR_ID + " integer primary key autoincrement,"
            + FR_NAME + " text not null,"
            + FR_LAT + " text not null,"
            + FR_LNG + " text not null);";

    DbHelper helper;
    SQLiteDatabase db;

    public DataBaseManager(Context context){
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String Name, String Lat, String Lng){
        ContentValues valores = new ContentValues();
        valores.put(FR_NAME,Name);
        valores.put(FR_LAT,Lat);
        valores.put(FR_LNG, Lng);
        return valores;
    }
    public boolean add(double latitude, double longitude, String nome, String subnome, String icon){
        if((searchByLocation(latitude,longitude)==false)&&(searchByName(nome)==false)) {
            db.insert(TABLE_NAME, null, generarContentValues(nome, Double.toString(latitude), Double.toString(longitude)));
            return true;
        }else{
            return false;
        }
    }
    public boolean replace(int index, double latitude, double longitude, String nome, String subnome, String icon) {
        if((index>=0)&&(index<size())) {
            Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, null, null, null, null, null);
            c.moveToPosition(index);
            db.update(TABLE_NAME, generarContentValues(nome, Double.toString(latitude), Double.toString(longitude)), FR_ID + "=?", new String[]{c.getString(0)});
            return true;
        }else
            return false;
    }
    public void remove(int i){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, null, null, null, null, null);
        c.moveToPosition(i);
        db.delete(TABLE_NAME, FR_ID + "=?", new String[]{c.getString(0)});
    }
    public boolean isEmpty(){
        //Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, FR_ID + "!=", new String[]{""}, null, null, null);
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            return false;
        }
        return true;
    }
    public Marcador get(int i){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, null, null, null, null, null);
        c.moveToPosition(i);
        return new Marcador(Double.parseDouble(c.getString(2)), Double.parseDouble(c.getString(3)), c.getString(1), null, MainActivity.getDogger_marker_tag());
    }
    public int size(){
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        return c.getCount();
    }
    public boolean searchByLocation(double latitude, double longitude){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, FR_LAT + "=?", new String[]{Double.toString(latitude)}, null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {  //Devuelve TRUE en caso de que se haga el movimiento sin errores.
            //Recorremos el cursor hasta que no haya más registros
            do {
                if(c.getString(3).compareTo(Double.toString(longitude))==0){
                    return true;
                };
            } while(c.moveToNext());
        }
        return false;
    }
    public boolean searchByName(String nome){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, FR_NAME + "=?", new String[]{nome}, null, null, null);
        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }
    public boolean searchByLocation(double latitude, double longitude, int index){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, null, null, null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {  //Devuelve TRUE en caso de que se haga el movimiento sin errores.
            //Recorremos el cursor hasta que no haya más registros
            do {
                if(c.getString(2).compareTo(Double.toString(latitude))==0) {
                    if (c.getString(3).compareTo(Double.toString(longitude)) == 0) {
                        if (c.getPosition() != index) {
                            return true;
                        }
                    }
                }
            } while(c.moveToNext());
        }
        return false;
    }
    public boolean searchByName(String nome, int index){
        Cursor c = db.query(TABLE_NAME, new String[]{FR_ID, FR_NAME, FR_LAT, FR_LNG}, null, null, null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {  //Devuelve TRUE en caso de que se haga el movimiento sin errores.
            //Recorremos el cursor hasta que no haya más registros
            do {
                if(c.getString(1).compareTo(nome)==0) {
                    if (c.getPosition() != index) {
                        return true;
                    }
                }
            } while(c.moveToNext());
        }
        return false;
    }
}
