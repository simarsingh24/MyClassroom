package com.svnit.harsimar.myclassroom;

import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApiNotAvailableException;

public class firebase extends AppCompatActivity {
    private Button sendbt;
    private EditText sendText;
    private EditText keyText;
    private Firebase mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mRootRef=new Firebase("https://myclassroom-11c78.firebaseio.com/users");

        keyText=(EditText)findViewById(R.id.firebase_key_et);
        sendbt=(Button)findViewById(R.id.firebase_bt);
        sendText=(EditText)findViewById(R.id.firebase_et);
        sendbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=sendText.getText().toString();
                Firebase childRef=mRootRef.child(keyText.getText().toString());
                //mRootRef.push().setValue(sendText);
                childRef.setValue(text);
            }
        });

    }
}
