package com.svnit.harsimar.myclassroom;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText nameField;
    private EditText passwordField;
    private EditText rollnoField;
    private EditText emailField;
    private Button register;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
/*
        mAuth=new FirebaseAuth() {
            @Nullable
            @Override
            public FirebaseUser getCurrentUser() {
                return super.getCurrentUser();
            }
        }*/
        nameField=(EditText)findViewById(R.id.name_register_et);
        passwordField=(EditText)findViewById(R.id.myClassroom_password_et);
        emailField=(EditText)findViewById(R.id.email_register_et);
        rollnoField=(EditText)findViewById(R.id.rollno_register_et);
        register=(Button)findViewById(R.id.register_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

    }

    private void startRegister() {
        String name=nameField.getText().toString().trim();
        String password=passwordField.getText().toString().trim();
        String email=emailField.getText().toString().trim();
        String rollno=rollnoField.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(email)&&
                !TextUtils.isEmpty(rollno)){


        }else {
            Toast.makeText(Register.this,"Please fill your details",Toast.LENGTH_SHORT).show();
        }

    }
}
