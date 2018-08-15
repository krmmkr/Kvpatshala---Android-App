package com.example.kohulrajm.test;

/**
 * Created by Kohul Raj M on 22-04-2017.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class Login extends AppCompatActivity implements View.OnClickListener {

    //  private TextView textViewJSON;
    //  private Button buttonGet;
    public EditText registerno;
    public EditText password;
    private Button buttonParse;
    public String str;

    public static final String MY_JSON ="MY_JSON";
   // public static String MY_JSON ="MY_JSON";

    public static final String JSON_URL = "http://kvpatshala.com/sample/android/android.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        registerno=(EditText) findViewById(R.id.REGISTERNO);
        password=(EditText) findViewById(R.id.PASSWORD);
        buttonParse = (Button) findViewById(R.id.buttonParse);

        buttonParse.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //     return true;
        //  }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //     if(v==buttonGet){
        //
        //    }
        if(v==buttonParse){
            getJSON(JSON_URL);

        }
    }
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }

    private void showParseActivity() {

        if (str == ""){
            // Username or password false, display and an error
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("Check Username and Password");
            dlgAlert.setTitle("Error Logging In");
           // dlgAlert.setPositiveButton("OK", null);
          //  dlgAlert.setCancelable(true);

            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    onRestart();
                 //   startActivity(getIntent());
                }
            });
            dlgAlert.create().show();

        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MY_JSON, str);
            startActivity(intent);
        }
    }
    //
    private void getJSON(String url) {
        final String regno = registerno.getText().toString().trim();
        final String passwd = password.getText().toString().trim();
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Please Wait...",null,true,true);
            }

            @Override
            protected String doInBackground(Void... V) {

                HashMap<String,String> params = new HashMap<>();
                params.put("REGISTERNO",regno);
                params.put("PASSWORD",passwd);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(JSON_URL, params);

                /*String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }*/
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  loading.dismiss();
                //  textViewJSON.setText(s);
                str = s;
                showParseActivity();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}

