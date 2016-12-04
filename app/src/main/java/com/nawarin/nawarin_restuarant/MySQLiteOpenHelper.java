package com.nawarin.nawarin_restuarant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Narrin on 19/10/2559.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Restuarant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table usertable "+
            "(_id integer primary key,user text,password text, name text);";
    private static final String CREATE_FOOD_TABLE = "create table foodtable "+
            "(_id integer primary key,food text,source text, price text);";
    private static final String CREATE_ORDER_TABLE = "create table ordertable "+
            "(_id integer primary key,officer text,desk text, food text , item text);";

    public MySQLiteOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
