package com.svnit.harsimar.myclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class FacultyAdd extends AppCompatActivity {

    private ImageButton facultyProfilePhoto;
    private EditText facultyName;
    private EditText facultyDesig;
    private EditText facultyDegree;
    private Button facultyDetailsSendBtn;

    private Uri facultyProfilePhotoUri;
    private static final int GALLERY_REQUEST=1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStorage= FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Faculty");

        facultyProfilePhoto=(ImageButton)findViewById(R.id.faculty_image_select_btn);
        facultyName=(EditText)findViewById(R.id.faculty_name_select_et);
        facultyDegree=(EditText)findViewById(R.id.faculty_degree_select_et);
        facultyDesig=(EditText)findViewById(R.id.faculty_desig_select_et);
        facultyDetailsSendBtn=(Button)findViewById(R.id.faculty_details_confirm);
        mProgress=new ProgressDialog(this);

        facultyProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_REQUEST);
            }
        });
        facultyDetailsSendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(facultyName.getText().toString().trim().isEmpty() ||
                        facultyDegree.getText().toString().trim().isEmpty()||
                        facultyDesig.getText().toString().trim().isEmpty()){
                    Toast.makeText(FacultyAdd.this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
                }else  startAdding();
            }
        });

    }

    private void startAdding() {

        mProgress.setMessage("Adding new faculty member...");
        final String name_val=facultyName.getText().toString().trim();
        final String desig_val=facultyDesig.getText().toString().trim();
        final String degree_val=facultyDegree.getText().toString().trim();
        if(!TextUtils.isEmpty(name_val)&& !TextUtils.isEmpty(desig_val)&& !TextUtils.isEmpty(degree_val)
                && facultyProfilePhotoUri!=null) {
            mProgress.show();
            StorageReference filepath=
                    mStorage.child("FacultyProfileImages").child(facultyProfilePhotoUri.getLastPathSegment());
            filepath.putFile(facultyProfilePhotoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Toast.makeText(FacultyAdd.this,"Done Adding.",Toast.LENGTH_SHORT).show();
                    DatabaseReference newPost=mDatabase.push();

                    newPost.child("name").setValue(name_val);
                    newPost.child("degree").setValue(degree_val);
                    newPost.child(("desig")).setValue(desig_val);
                    newPost.child("profilePhoto").setValue(downloadUrl.toString());
                    mProgress.dismiss();
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FacultyAdd.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                }
            });



        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST){
            facultyProfilePhotoUri=data.getData();
            CropImage.activity(facultyProfilePhotoUri).setGuidelines(CropImageView.Guidelines.ON).start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                facultyProfilePhoto.setImageURI(resultUri);
                facultyProfilePhotoUri=resultUri;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
