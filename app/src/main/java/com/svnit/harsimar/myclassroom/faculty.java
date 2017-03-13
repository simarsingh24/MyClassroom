package com.svnit.harsimar.myclassroom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class faculty extends AppCompatActivity {
    private RecyclerView facultyRecycler;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Faculty");
        mDatabaseReference.keepSynced(true);

        facultyRecycler=(RecyclerView)findViewById(R.id.recycler_faculty);
        facultyRecycler.setHasFixedSize(true);
        facultyRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<faculty_data,FacultyViewHolder> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<faculty_data, FacultyViewHolder>(
                        faculty_data.class,
                        R.layout.row_faculty,
                        FacultyViewHolder.class,
                        mDatabaseReference)
                {
            @Override
            protected void populateViewHolder(FacultyViewHolder viewHolder, faculty_data model, int position) {
                viewHolder.setFacultyDegree(model.getDegree());
                viewHolder.setFacultyName(model.getName());
                viewHolder.setFacultyDesig(model.getDesig());
                viewHolder.setFacultyProfilePhoto(getApplicationContext(),model.getProfilePhoto());
            }
        };
        facultyRecycler.setAdapter(firebaseRecyclerAdapter);
    }
    public static class FacultyViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public FacultyViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setFacultyName(String name){
            TextView faculty_name =(TextView)mView.findViewById(R.id.faculty_name);
            faculty_name.setText(name);
        }
        public void setFacultyDesig(String desig){
            TextView faculty_desig =(TextView)mView.findViewById(R.id.faculty_designation);
            faculty_desig.setText(desig);
        }
        public void setFacultyDegree(String degree){
            TextView faculty_degree =(TextView)mView.findViewById(R.id.faculty_degree);
            faculty_degree.setText(degree);
        }
        public void setFacultyProfilePhoto(Context context, String imageUri){
            final ImageView faculty_photo=(ImageView)mView.findViewById(R.id.faculty_profile_photo_iv);
            Picasso.with(context).load(imageUri).into(faculty_photo);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bc_connect_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add_post){
            startActivity(new Intent(this,FacultyAdd.class));
        }
        if(item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
