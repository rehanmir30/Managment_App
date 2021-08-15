package com.example.managmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class OpenProduct extends AppCompatActivity {

    TextView name, price, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_product);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);

        Intent i = getIntent();
        String p_name = i.getStringExtra("p_name");
        String p_price = i.getStringExtra("p_price");
        String p_description = i.getStringExtra("p_description");
        String p_image = i.getStringExtra("p_image");

        name.setText(p_name);
        price.setText(p_price);
        description.setText(p_description);

        String imageUri = "https://firebasestorage.googleapis.com/v0/b/managment-app-b9d30.appspot.com/o/images%2F" + p_image + "?alt=media&token=3827a434-9e70-4e86-9fac-7848e598f469";

        Picasso.get().load(p_image).into(image);

    }

    public void goback(View v) {
        Intent i = new Intent(getApplicationContext(), Product_Details.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Product_Details.class);
        startActivity(i);
        finish();
    }
}