package com.example.managmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class AddSales extends AppCompatActivity {

    EditText p_name, p_price, p_quantity;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String dataBase_url = "https://managment-app-b9d30-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);

        p_name = findViewById(R.id.p_name);
        p_price = findViewById(R.id.p_price);
        p_quantity = findViewById(R.id.p_quantity);

        database = FirebaseDatabase.getInstance(dataBase_url);
        myRef = database.getReference("Sold");
        Date currentTime = Calendar.getInstance().getTime();

    }

    public void addSales(View v) {
        String name = p_name.getText().toString().trim();
        String price = p_price.getText().toString().trim();
        String quantity = p_quantity.getText().toString().trim();

        if (name.isEmpty()||price.isEmpty()||quantity.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fill all fields please",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int product_count = (int) dataSnapshot.getChildrenCount();
                    Date currentTime = Calendar.getInstance().getTime();

                    myRef.child(String.valueOf(product_count + 1)).child("Name").setValue(name);
                    myRef.child(String.valueOf(product_count + 1)).child("Quantity").setValue(quantity);
                    myRef.child(String.valueOf(product_count + 1)).child("Price").setValue(price);
                    myRef.child(String.valueOf(product_count + 1)).child("Time").setValue(String.valueOf(currentTime));

                    Intent i=new Intent(getApplicationContext(),Sales_Screen.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            Toast.makeText(getApplicationContext(), "Sold Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Sales_Screen.class);
        startActivity(i);
        finish();
    }
}