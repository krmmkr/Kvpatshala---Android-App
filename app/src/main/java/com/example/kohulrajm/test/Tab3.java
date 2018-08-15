package com.example.kohulrajm.test;

/**
 * Created by Kohul Raj M on 22-04-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

//Our class extending fragment
public class Tab3 extends Fragment {
    private int TRACK = 0;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
      //  return inflater.inflate(R.layout.tab3, container, false);

       View v = inflater.inflate(R.layout.tab3, container, false);
        showData(v);

        return v;

    }
    private void showData(View v){

        String[] title = {
                "Vaccination",
                "Remarks",
                "Updates"
        };
        String[] value = new String[title.length];

        try {
            JSONObject jsonObject = MainActivity.studenthealth.getJSONObject(TRACK);
            for (int i = 0; i < title.length; i++) {
                value[i] = jsonObject.getString(title[i]);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView studenthealth = (TextView) v.findViewById(R.id.studenthealth);
        for(int i=0; i<title.length; i++){
            if(value[i] != null && !value[i].isEmpty() && !value[i].trim().isEmpty()){
                studenthealth.append(System.getProperty ("line.separator") +
                        title[i] + "  :  " + value[i] + System.getProperty ("line.separator"));
            }else{
                studenthealth.append(System.getProperty ("line.separator") +
                        title[i] + "  :  " + "No Data" + System.getProperty ("line.separator"));
            }

        }

    }
}