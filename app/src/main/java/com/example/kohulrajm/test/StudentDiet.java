package com.example.kohulrajm.test;

/**
 * Created by Kohul Raj M on 23-04-2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

//Our class extending fragment

public class StudentDiet extends Fragment implements AdapterView.OnItemSelectedListener{
    private int TRACK = 10;
    TextView breakfast, lunch, snacks, dinner;

    //Overriden method onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View v = inflater.inflate(R.layout.studentdiet, container, false);
        //

        Spinner spn = (Spinner) v.findViewById(R.id.spinner);
        breakfast = (TextView) v.findViewById(R.id.mbreakfast);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerItems, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spn.setAdapter(adapter);

        spn.setOnItemSelectedListener(this);
        showData();
        return v;

    }
    String[] title = {
            "Day",
            "Breakfast",
            "Lunch",
            "Snacks",
            "Dinner",
            "Remarks"
    };
    String[] value = new String[title.length];

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);

      // String item = parent.getItemAtPosition(position).toString();

        try {
            for(int i=0; i<7; i++){
                JSONObject jsonObject = MainActivity.studentdiet.getJSONObject(i);
                if(jsonObject.getString("Day").equalsIgnoreCase(parent.getItemAtPosition(position).toString())){
                    TRACK = i;
                    showData();
                    break;
                }else{
                    TRACK = 10;
                    showData();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showData(){

        try {
            JSONObject jsonObject = MainActivity.studentdiet.getJSONObject(TRACK);

            for(int i=0; i<title.length; i++) {
                value[i] = jsonObject.getString(title[i]);
            }

          //  breakfast.setText(jsonObject.getString("Breakfast"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
       // lunch.setText(" " +TRACK);
        breakfast.setText("");
        for(int i=0; i<title.length; i++){
            if(value[i] != null && !value[i].isEmpty() && !value[i].trim().isEmpty() && TRACK != 10){
                breakfast.append(System.getProperty ("line.separator") +
                        title[i] + "  :  " + value[i] + System.getProperty ("line.separator"));
            }else{
                breakfast.append(System.getProperty ("line.separator") +
                        title[i] + "  :  " + "No Data" + System.getProperty ("line.separator"));
            }

        }

        }

    }