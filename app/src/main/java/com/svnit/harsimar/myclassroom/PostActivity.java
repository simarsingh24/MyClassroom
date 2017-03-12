package com.svnit.harsimar.myclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {
    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitBtn;
    private Uri mImageUri;
    private static final int GALLERY_REQUEST=1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    //private Firebase mDatabaseRoot;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage= FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("MyClassroom");
        //mDatabaseRoot= new Firebase("https://myclassroom-11c78.firebaseio.com/MyClassroom");

        mSelectImage=(ImageButton)findViewById(R.id.post_ib);
        mPostDesc =(EditText) findViewById(R.id.post_description_et);
        mPostTitle=(EditText) findViewById(R.id.post_title_et);
        mSubmitBtn=(Button)findViewById(R.id.submit_post_btn);

        mProgress=new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_REQUEST);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(mPostTitle.getText().toString().trim().isEmpty() ||
                        mPostDesc.getText().toString().trim().isEmpty()){
                    Toast.makeText(PostActivity.this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
                }else  startPosting();
            }
        });

    }

    private void startPosting() {
        mProgress.setMessage("Posting your message...");
        final String title_val=mPostTitle.getText().toString().trim();
        final String desc_val= mPostDesc.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val)&& !TextUtils.isEmpty(desc_val) && mImageUri!=null){
            mProgress.show();
            StorageReference filepath=
                    mStorage.child("Attached Images").child(mImageUri.getLastPathSegment());
                    filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(PostActivity.this,"Done Uploading.",Toast.LENGTH_SHORT).show();

                            DatabaseReference newPost=mDatabase.push();
                            Log.d("harsimarSINGH",newPost.toString());
                            newPost.child("title").setValue(title_val);
                            newPost.child("desc").setValue(desc_val);
                            newPost.child("image").setValue(downloadUrl.toString());

                          /*Firebase childRef=mDatabaseRoot.push();
                            childRef.child("title").setValue(title_val);
                            childRef.child("desc").setValue(desc_val);
                            childRef.child("image").setValue(downloadUrl.toString());
                            */
                            mProgress.dismiss();
                            finish();

                    }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                            mProgress.dismiss();
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==GALLERY_REQUEST){
            mImageUri=data.getData();
            mSelectImage.setImageURI(mImageUri);
            }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
