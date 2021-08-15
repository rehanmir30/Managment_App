package com.example.managmentapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProduct extends AppCompatActivity {

    TextInputEditText pro_name, pro_price, pro_description;
    CircleImageView pro_image;
    Button addSend;

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    String imageName;
    String dataBase_url = "https://managment-app-b9d30-default-rtdb.firebaseio.com/";

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseStorage storage;
    StorageReference storageReference;

    int image_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        pro_description = findViewById(R.id.pro_description);
        pro_name = findViewById(R.id.pro_name);
        pro_price = findViewById(R.id.pro_price);
        pro_image = findViewById(R.id.product_image);
        addSend = findViewById(R.id.addsend);

        database = FirebaseDatabase.getInstance(dataBase_url);
        myRef = database.getReference("Products");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }

    public void goBack(View v) {
        Intent i = new Intent(getApplicationContext(), Product_Details.class);
        startActivity(i);
        finish();
    }

    public void addSend(View v) {
        String name = pro_name.getText().toString().trim();
        String price = pro_price.getText().toString().trim();
        String description = pro_description.getText().toString().trim();

        if (name.isEmpty()) {
            pro_name.setError("Name Required");
            return;
        } else if (price.isEmpty()) {
            pro_price.setError("Price Required");
            return;
        } else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int product_count = (int) dataSnapshot.getChildrenCount();

                    image_number = product_count + 1;
                    myRef.child(String.valueOf(product_count + 1)).child("Name").setValue(name);
                    myRef.child(String.valueOf(product_count + 1)).child("Description").setValue(description);
                    myRef.child(String.valueOf(product_count + 1)).child("Price").setValue(price);
//                    myRef.child(String.valueOf(product_count + 1)).child("image").setValue(imageName);

                    uploadImage(product_count+1);


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();


        }
    }

    private void uploadImage(int image_number) {
//        ProgressDialog progressDialog
//                = new ProgressDialog(this);
//        progressDialog.setTitle("Uploading...");
//        progressDialog.show();

        imageName = UUID.randomUUID().toString();

        myRef.child(String.valueOf(image_number)).child("image").setValue(imageName);

        Log.d("******",imageName);
        // Defining the child of storageReference
        StorageReference ref
                = storageReference
                .child(
                        "images/"
                                + imageName);


        ref.putFile(filePath)
                .addOnSuccessListener(
                        new OnSuccessListener<UploadTask.TaskSnapshot>() {

                            @Override
                            public void onSuccess(
                                    UploadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(getApplicationContext(),"Product uploaded successfully",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddProduct.this, Product_Details.class);
                                startActivity(i);
                                finish();
                                // Image uploaded successfully
                                // Dismiss dialog
//                                progressDialog.dismiss();
//                                Toast
//                                        .makeText(getApplicationContext(),
//                                                "Image Uploaded!!",
//                                                Toast.LENGTH_SHORT)
//                                        .show();
                            }
                        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Error, Image not uploaded
//                        progressDialog.dismiss();
//                        Toast
//                                .makeText(getApplicationContext(),
//                                        "Failed " + e.getMessage(),
//                                        Toast.LENGTH_SHORT)
//                                .show();
                    }
                })
                .addOnProgressListener(
                        new OnProgressListener<UploadTask.TaskSnapshot>() {

                            // Progress Listener for loading
                            // percentage on the dialog box
                            @Override
                            public void onProgress(
                                    UploadTask.TaskSnapshot taskSnapshot) {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
//                                progressDialog.setMessage(
//                                        "Uploaded "
//                                                + (int) progress + "%");
                            }
                        });
    }


    public void openGallery(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            //imageName=getPath(getApplicationContext( ), filePath );

            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), filePath);
                pro_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Product_Details.class);
        startActivity(i);
        finish();
    }
}