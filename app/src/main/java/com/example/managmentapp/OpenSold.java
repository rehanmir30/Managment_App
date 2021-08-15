package com.example.managmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OpenSold extends AppCompatActivity {

    ImageView goback;

    TextView Name, Price, Quanti, Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_sold);

        goback = findViewById(R.id.goback);
        Name = findViewById(R.id.name);
        Price = findViewById(R.id.price);
        Quanti = findViewById(R.id.quant);
        Time = findViewById(R.id.time);

        Intent i = getIntent();
        String name = i.getStringExtra("s_name");
        String price = i.getStringExtra("s_price");
        String time = i.getStringExtra("s_time");
        String quantity = i.getStringExtra("s_quantity");

        Name.setText(name);
        Price.setText(price);
        Quanti.setText(quantity);
        Time.setText(time);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Sales_Screen.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Sales_Screen.class);
        startActivity(i);
        finish();
    }
}