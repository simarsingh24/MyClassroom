package com.svnit.harsimar.myclassroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class databasecheck extends AppCompatActivity {
    private RecyclerView bc_connect_recycler;
    private Firebase mDatabaseroot;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databasecheck);
        setContentView(R.layout.activity_bc_connect);

        mDatabaseroot=new Firebase("https://myclassroom-11c78.firebaseio.com/MyClassroom");

        bc_connect_recycler=(RecyclerView)findViewById(R.id.recycler_bc_connect);
        bc_connect_recycler.setHasFixedSize(true);
        bc_connect_recycler.setLayoutManager(new LinearLayoutManager(this));

        mDatabaseroot.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                Log.d("harsimarSINGH","childADDED");
                Log.d("harsimarSINGH",dataSnapshot.child("title").getValue().toString());
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                Log.d("harsimarSINGH","removed");
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                Log.d("harsimarSINGH","moved");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("harsimarSINGH","erreor");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bc_connect_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add_post){
            startActivity(new Intent(this,PostActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
