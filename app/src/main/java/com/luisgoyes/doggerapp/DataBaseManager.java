package com.luisgoyes.doggerapp;

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

}
