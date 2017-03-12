package com.svnit.harsimar.myclassroom;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
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

public class bc_connect extends AppCompatActivity {
    private RecyclerView mBcConnectRecycler;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bc_connect);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("MyClassroom");
        mDatabase.keepSynced(true);

        /*
        I wonder if ill ever be able to write something as beautiful and nostalgic,
                like the curiously gentle way you smile
                and if i never happen to stumble upon such words,
                i suppose settling would be the next great adventure!

        */

        
        mBcConnectRecycler=(RecyclerView)findViewById(R.id.recycler_bc_connect);
        mBcConnectRecycler.setHasFixedSize(true);
        mBcConnectRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<bc_connect_posts,BcConnectViewHolder> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<bc_connect_posts, BcConnectViewHolder>(
                        bc_connect_posts.class,
                        R.layout.row_bc_connect_activity,
                        BcConnectViewHolder.class,
                        mDatabase
                ) {
            @Override
            protected void populateViewHolder(BcConnectViewHolder viewHolder, bc_connect_posts model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());

            }
        };
        mBcConnectRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BcConnectViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public BcConnectViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title){
            TextView post_title =(TextView)mView.findViewById(R.id.post_title_bc_connect_tv);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc =(TextView)mView.findViewById(R.id.post_desc_bc_connect_tv);
            post_desc.setText(desc);
        }
        public void setImage(Context context, String imageUri){
            ImageView post_image=(ImageView)mView.findViewById(R.id.post_image_bc_connect_iv);
            Picasso.with(context).load(imageUri).into(post_image);
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
            startActivity(new Intent(this,PostActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}

