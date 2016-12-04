package com.nawarin.nawarin_restuarant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText userSign,passSign,nameSign;
    private String userStr,passStr,nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        bindWidget();
    }

    private void bindWidget(){
        userSign = (EditText) findViewById(R.id.usersigntxt);
        passSign = (EditText) findViewById(R.id.passsigntxt);
        nameSign = (EditText) findViewById(R.id.namesigntxt);
    }

    public void clickSignUpSign(View view){
        userStr = userSign.getText().toString().trim();
        passStr = passSign.getText().toString().trim();
        nameStr = nameSign.getText().toString().trim();

        if(userStr.equals("") || passStr.equals("") || nameStr.equals("")){
            //Have space
            Toast.makeText(getApplicationContext(),"Please insert every field",Toast.LENGTH_SHORT).show();

        }else {
            //No Space

            updateValueToServer();
        }
    }

    private void updateValueToServer(){


        AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String strURL = "http://csclub.ssru.ac.th/s56122201015/food/php_add_user.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("isAdd","true");
                formBuilder.add("User",userStr);
                formBuilder.add("Password",passStr);
                formBuilder.add("Name",nameStr);
                RequestBody body = formBuilder.build();
                try{
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url(strURL).post(body).build();
                    Response response = okHttpClient.newCall(request).execute();


                }catch (Exception e){

                    Log.d("Narrin","Add user ==> "+e.toString());

                }
                return null;
            }
        };
        task.execute();
    }
}
