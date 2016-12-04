package com.nawarin.nawarin_restuarant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView = (ListView) findViewById(R.id.listView);

        MySyncJSON mySyncJSON = new MySyncJSON();
        mySyncJSON.execute();


    }

    public class MySyncJSON extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            try{
                String strUrl = "http://www.csclub.ssru.ac.th/s56122201015/food/php_get_foodTABLE.php";
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
                String[] strIcon = new String[jsonArray.length()];
                String[] strName = new String[jsonArray.length()];
                String[] strPrice = new String[jsonArray.length()];

                for(int i = 0; i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    strName[i] = object.getString("Food");
                    strIcon[i] = object.getString("Source");
                    strPrice[i] = object.getString("Price");



                }
                CustomAdaptor adaptor = new CustomAdaptor(ShowActivity.this,strIcon,strName,strPrice);
                listView.setAdapter(adaptor);
                Toast.makeText(ShowActivity.this,"Syncronitz mySQL to SQLite Finish",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.d("Narrin","on Post ==> "+s);
            }

        }
    }
}
