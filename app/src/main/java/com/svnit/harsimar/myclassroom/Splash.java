package com.svnit.harsimar.myclassroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
public class Splash extends Activity {
    final int sleep_time=2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(sleep_time);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent startMainActivity=new Intent("com.svnit.harsimar.myclassroom.StartMenu");
                    startActivity(startMainActivity);
                };
            };
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
