package com.example.kohulrajm.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.support.v4.app.DialogFragment;

//Implementing the interface OnTabSelectedListener to our MainActivity
//This interface would help in swiping views

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    //vars
    private String myJSONString;
    public static JSONArray studentdetails = null;
    public static JSONArray studentmark = null;
    public static JSONArray studenthealth = null;
    public static JSONArray studentdiet = null;
    public static JSONArray updates = null;
    private static final String JSON_ARRAY1 ="studentdetails";
    private static final String JSON_ARRAY2 ="studentmark";
    private static final String JSON_ARRAY3 ="studenthealth";
    private static final String JSON_ARRAY4 ="dietchart";

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //activity
        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(Login.MY_JSON);
        extractJSON();

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Updates"));
        tabLayout.addTab(tabLayout.newTab().setText("Student Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Student Marks"));
        tabLayout.addTab(tabLayout.newTab().setText("Student Health"));
        tabLayout.addTab(tabLayout.newTab().setText("Student Diet"));
        //tabLayout.addTab(tabLayout.newTab().setText("Student Diet2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

    }

    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            studentdetails = jsonObject.getJSONArray(JSON_ARRAY1);
            studentmark = jsonObject.getJSONArray(JSON_ARRAY2);
            studenthealth = jsonObject.getJSONArray(JSON_ARRAY3);
            studentdiet = jsonObject.getJSONArray(JSON_ARRAY4);
            updates = jsonObject.getJSONArray("updates");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onBackPressed() {
        AlertDialog exitbox =new AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        exitbox.show();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
