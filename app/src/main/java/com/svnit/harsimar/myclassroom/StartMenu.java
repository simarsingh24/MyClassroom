package com.svnit.harsimar.myclassroom;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StartMenu extends ListActivity {
    String classes[] = {"MainActivity", "ACT2", "ACT3", "ACT4"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
        setListAdapter(classAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String launchingClassName = classes[position];
        try {
            Class launchingClass = Class.forName("com.svnit.harsimar.myclassroom." + launchingClassName);
            Intent launchIntent = new Intent(this, launchingClass);
            startActivity(launchIntent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ;
    }

}