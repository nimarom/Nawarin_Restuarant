package com.nawarin.nawarin_restuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Narrin on 19/10/2559.
 */
public class UserTable {
    public static final String user_table = "usertable";
    public static final String column_id = "_id";
    public static final String column_user = "user";
    public static final String column_password = "password";
    public static final String column_name = "name";

    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSqLiteDatabase;

    public UserTable(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addNewUser(String strUser,String strPassword,String strName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_user,strUser);
        contentValues.put(column_password,strPassword);
        contentValues.put(column_name,strName);
        return writeSqLiteDatabase.insert(user_table,null,contentValues);
    }

}
