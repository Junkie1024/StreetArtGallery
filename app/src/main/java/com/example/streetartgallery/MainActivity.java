package com.example.streetartgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText userid,pswd;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid = findViewById(R.id.userid);
        pswd = findViewById(R.id.pswd);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userid.getText().toString();
                String pass = pswd.getText().toString();
                if(TextUtils.isEmpty(id)||TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Fields Missing!!!",Toast.LENGTH_LONG).show();
                }
                else {
                    new MyTask(getApplicationContext(),id, pass).execute();
                }
            }
        });
    }

    private class MyTask extends AsyncTask<Void, Void, String> {
        String message;
        String id,pass;
        Context context;

        public MyTask(Context context,String id, String pass) {
            this.context = context;
            this.id = id;
            this.pass = pass;
        }

        @Override
        protected String doInBackground(Void... params){

            URL url = null;
            try {

                url = new URL("http://192.168.3.109:8080/StreetArtGallery/streetart/database/login&"+id+"&"+pass);

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput= new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());

                message = response.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            return message;

        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
            parseJsonData(result);
        }
        private void parseJsonData(String jsonResponse){
            try
            {
                JSONObject responseObj = new JSONObject(jsonResponse);
                Toast.makeText(context,responseObj.getString("message"),Toast.LENGTH_LONG).show();
                if(responseObj.getString("Status").equals("OK")){
                 startActivity(new Intent(MainActivity.this,Artist_List_Activity.class));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

    }
}
