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
public class Tab2 extends Fragment {

    private int TRACK = 0;

    //Overriden method onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.tab2, container, false);

        showData(v);

        return v;
    }
    private void showData(View v){
        try {
            JSONObject jsonObject = MainActivity.studentmark.getJSONObject(TRACK);
          //  TextView registerno = (TextView) v.findViewById(R.id.sregisterno);
           /* registerno.setText(jsonObject.getString("registerno"));
            TextView sclass = (TextView) v.findViewById(R.id.sclass);
            sclass.setText(jsonObject.getString("class"));
            TextView section = (TextView) v.findViewById(R.id.ssection);
            section.setText(jsonObject.getString("section"));*/
            TextView term = (TextView) v.findViewById(R.id.sterm);
            term.setText(jsonObject.getString("term"));
            TextView subject1 = (TextView) v.findViewById(R.id.ssubject1);
            subject1.setText(jsonObject.getString("subject1"));
            TextView subject2 = (TextView) v.findViewById(R.id.ssubject2);
            subject2.setText(jsonObject.getString("subject2"));
            TextView subject3 = (TextView) v.findViewById(R.id.ssubject3);
            subject3.setText(jsonObject.getString("subject3"));
            TextView subject4 = (TextView) v.findViewById(R.id.ssubject4);
            subject4.setText(jsonObject.getString("subject4"));
            TextView subject5 = (TextView) v.findViewById(R.id.ssubject5);
            subject5.setText(jsonObject.getString("subject5"));
            TextView subject6 = (TextView) v.findViewById(R.id.ssubject6);
            subject6.setText(jsonObject.getString("subject6"));
            TextView subject7 = (TextView) v.findViewById(R.id.ssubject7);
            subject7.setText(jsonObject.getString("subject7"));
            TextView subject8 = (TextView) v.findViewById(R.id.ssubject8);
            subject8.setText(jsonObject.getString("subject8"));
            TextView subject9 = (TextView) v.findViewById(R.id.ssubject9);
            subject9.setText(jsonObject.getString("subject9"));
            TextView subject10 = (TextView) v.findViewById(R.id.ssubject10);
            subject10.setText(jsonObject.getString("subject10"));
           /*  TextView subject11 = (TextView) v.findViewById(R.id.ssubject11);
            subject11.setText(jsonObject.getString("subject11"));
            TextView subject12 = (TextView) v.findViewById(R.id.ssubject12);
            subject12.setText(jsonObject.getString("subject12"));*/
            TextView total = (TextView) v.findViewById(R.id.stotal);
            total.setText(jsonObject.getString("total"));
            TextView rank = (TextView) v.findViewById(R.id.srank);
            rank.setText(jsonObject.getString("rank"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}