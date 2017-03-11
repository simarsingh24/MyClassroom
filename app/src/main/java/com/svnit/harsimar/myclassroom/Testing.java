package com.svnit.harsimar.myclassroom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class Testing extends AppCompatActivity {
    private final int GALLERY_REQUEST = 1;
    String imgDecodableString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        Log.d("harsimarTAG","inside oncreate");
        ImageButton ib = (ImageButton) findViewById(R.id.imageTesting);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                Log.d("harsimarTAG","inside click");
                startActivityForResult(
                        Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST ) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Log.d("harsimarTAG", "inside if");
            Log.d("harsimarTAG", selectedImage.toString());
            ImageButton imgView = (ImageButton) findViewById(R.id.imageTesting);
            imgView.setImageURI(selectedImage);

        }
   }
}
