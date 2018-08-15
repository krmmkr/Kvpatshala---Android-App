package com.example.kohulrajm.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

/**
 * Created by Kohul Raj M on 24-05-2017.
 */

public class autologin {
    public String str;
    public String regno = "5";
    public String passwd = "test";
    public static final String MY_JSON ="MY_JSON";
    // public static String MY_JSON ="MY_JSON";

    public static final String JSON_URL = "http://kvpatshala.com/sample/android/android.php";

    public autologin(){
      // regno = SaveSharedPreference.getUserName(autologin.this);
        getJSON();

    }

    public String showParseActivity() {

       /* if (str == ""){
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
        }*/
        return str;
    }

    private void getJSON() {

        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
             //   loading = ProgressDialog.show(autologin.this, "Please Wait...",null,true,true);
            }

            @Override
            protected String doInBackground(Void... V) {

                HashMap<String,String> params = new HashMap<>();
                params.put("REGISTERNO",regno);
                params.put("PASSWORD",passwd);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(JSON_URL, params);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //  loading.dismiss();
                //  textViewJSON.setText(s);
                str = s;

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
