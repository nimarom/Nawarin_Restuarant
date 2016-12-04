package com.nawarin.nawarin_restuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Narrin on 19/10/2559.
 */
public class FoodTable {

    public static final String food_table = "foodtable";
    public static final String column_id = "_id";
    public static final String column_food = "food";
    public static final String column_source = "source";
    public static final String column_price = "price";

    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSqLiteDatabase;

    public FoodTable(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addNewFood(String strFood,String strSource,String strPrice){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_food,strFood);
        contentValues.put(column_source,strSource);
        contentValues.put(column_price,strPrice);
        return writeSqLiteDatabase.insert(food_table,null,contentValues);
    }
}
