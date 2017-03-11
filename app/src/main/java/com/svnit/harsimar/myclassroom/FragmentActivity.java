package com.svnit.harsimar.myclassroom;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        MainFragment mfrag=new MainFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        //ft.add(android.R.id.content,mfrag,"TAG");
        ft.add(R.id.container,mfrag,"harsimarTAG");
        ft.commit();

    }
}
