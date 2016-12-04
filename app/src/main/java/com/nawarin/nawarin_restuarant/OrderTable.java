package com.nawarin.nawarin_restuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Narrin on 19/10/2559.
 */
public class OrderTable {

    public static final String order_table = "ordertable";
    public static final String column_id = "_id";
    public static final String column_officer = "officer";
    public static final String column_desk = "desk";
    public static final String column_food = "food";
    public static final String column_item = "item";

    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSqLiteDatabase;

    public OrderTable(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addNewOrder(String strOfficer,String strDesk,String strFood,String strItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_food,strFood);
        contentValues.put(column_desk,strDesk);
        contentValues.put(column_officer,strOfficer);
        contentValues.put(column_item,strItem);
        return writeSqLiteDatabase.insert(order_table,null,contentValues);
    }
}
