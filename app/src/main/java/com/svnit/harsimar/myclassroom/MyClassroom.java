package com.svnit.harsimar.myclassroom;

import android.app.Application;
import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;
//import com.squareup.picasso.OkHttpDownloader;
//import com.squareup.picasso.Picasso;

public class MyClassroom extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
/*
        Picasso.Builder builder =new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
*/
    }
}
