package com.example.kohulrajm.test;

/**
 * Created by Kohul Raj M on 23-04-2017.
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
public class Updates extends Fragment {
    private int TRACK = 0;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View v = inflater.inflate(R.layout.updates, container, false);

        showData(v);

        return v;
    }

    private void showData(View v){
        int count = 0;
        String[] title = {
                "Date",
                "Class",
                "Section",
                "Tamil",
                "English",
                "Hindi",
                "Maths",
                "Science",
                "Physics",
                "Chemistry",
                "Botany",
                "Zoology",
                "Social Science",
                "History",
                "Geography",
                "Other Updates"
        };
        String[] value = new String[title.length];
        try {
            JSONObject jsonObject = MainActivity.updates.getJSONObject(TRACK);

          for(int i=0; i<title.length; i++) {
              value[i] = jsonObject.getString(title[i]);

          }

        } catch (JSONException e) {
            e.printStackTrace();
        }



      //  value[1] = "test1wewegesthrjdtyjtyuktuktyktdyjeryjrtyjyfgjygfcthrdtadvdfnthgxgjdrydgnjy";
       // value[3] = "test2";

        TextView updates = (TextView) v.findViewById(R.id.updates);
        updates.setText("Recent Updates" + System.getProperty ("line.separator"));
        for(int i=0; i<title.length; i++){
            if(value[i] != null && !value[i].isEmpty() && !value[i].trim().isEmpty()){
                updates.append(System.getProperty ("line.separator") +
                        title[i] + "  :  " + value[i] + System.getProperty ("line.separator"));
                count++;
            }

        }
        if(count == 0){
            updates.setText("No Recent Updates Available");
        }
    }
}