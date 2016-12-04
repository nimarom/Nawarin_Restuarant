package com.nawarin.nawarin_restuarant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private UserTable objUserTable;
    private OrderTable objOrderTable;
    private FoodTable objFoodTable;
    private MySQLite sqLite;

    private EditText usertxt,passtxt;
    private String strUser,strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        //Connected SQLite
        connectedSQLite();

        //test add value
        //testAddValue();

        sqLite = new MySQLite(this);

        MySyncJSON mySyncJSON = new MySyncJSON();
        mySyncJSON.execute();



    }

    private void connectedSQLite(){
        objUserTable = new UserTable(this);
        objOrderTable = new OrderTable(this);
        objFoodTable = new FoodTable(this);
    }

    private void testAddValue(){
        objUserTable.addNewUser("testUser","testPass","testName");
        objFoodTable.addNewFood("testFood","testSource","testPrice");
        objOrderTable.addNewOrder("testOffice","testDesk","testFood","testItem");
    }

    private  void syncAndDelete(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME,MODE_PRIVATE,null);
        sqLiteDatabase.delete(MySQLite.user_table,null,null);

    }

    public class MySyncJSON extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {

            try{
                String strUrl = "http://www.csclub.ssru.ac.th/s56122201015/food/php_get_userTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strUrl).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                Log.d("Narrin","Do in background ==> "+e.toString());
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Narrin","strJSON ==> "+s);

            try{
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String strUser = object.getString("User");
                    String strPassword = object.getString("Password");
                    String strName = object.getString("Name");
                    sqLite.addNewUser(strUser,strPassword,strName);
                }
                Toast.makeText(MainActivity.this,"Syncronitz mySQL to SQLite Finish",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.d("Narrin","on Post ==> "+s);
            }

        }
    }

    private void initWidget(){
        usertxt = (EditText) findViewById(R.id.usertxt);
        passtxt = (EditText) findViewById(R.id.passtxt);
    }

    public void clickSignInMain(View view){
        strUser = usertxt.getText().toString().trim();
        strPass = passtxt.getText().toString().trim();

        if(strUser.equals("") || strPass.equals("")){
            MyAlert alert = new MyAlert();
            alert.myDialog(MainActivity.this,"Incorrect","Please input all field");
        }else{
            checkUser();
        }
    }

    public void clickSignUp(View view){
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    private void checkUser(){
        try{
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME,MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM usertable where user = '"+strUser+"'",null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for(int i = 0;i < cursor.getColumnCount(); i++){
                resultStrings[i] = cursor.getString(i);
            }
            cursor.close();

            if(passtxt.getText().toString().trim().equals(resultStrings[2])){
                Toast.makeText(getApplicationContext(),"Log in success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("Result",resultStrings);
                startActivity(intent);
                finish();
            }else {
                MyAlert alert = new MyAlert();
                alert.myDialog(MainActivity.this,"Alert",passtxt.getText().toString().trim()+" "+resultStrings[2]);
            }
        }catch (Exception e){
            MyAlert alert = new MyAlert();
            alert.myDialog(MainActivity.this,"Alert","No user in database");
        }

    }
}
