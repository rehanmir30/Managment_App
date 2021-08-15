package com.example.managmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managmentapp.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Product_Details extends AppCompatActivity {

    private ArrayList<Product> Product_List;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mRecyclerView = findViewById(R.id.recyclerView);
        Product_List = new ArrayList<>();

        String dataBase_url = "https://managment-app-b9d30-default-rtdb.firebaseio.com/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(dataBase_url);
        DatabaseReference myRef = database.getReference("Products");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total_products = (int) dataSnapshot.getChildrenCount();
                if (total_products < 1) {
                    Toast.makeText(getApplicationContext(), "No Product to show", Toast.LENGTH_LONG).show();
                } else {

                    for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {

                        if (dataSnapshot.child(String.valueOf(i)).exists()) {
                            String naam = (String) dataSnapshot.child(String.valueOf(i)).child("Name").getValue();
                            String keemat = (String) dataSnapshot.child(String.valueOf(i)).child("Price").getValue();
                            String tafseel = (String) dataSnapshot.child(String.valueOf(i)).child("Description").getValue();
                            String tasweer = (String) dataSnapshot.child(String.valueOf(i)).child("image").getValue();

                            Product product = new Product(naam, keemat, tafseel, tasweer);
                            Product_List.add(product);

                        }

                    }

                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(Product_Details.this, LinearLayoutManager.VERTICAL, false);
                    mAdapter = new ExampleAdapter(Product_List);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent i = new Intent(getApplicationContext(), OpenProduct.class);
                            i.putExtra("p_name", Product_List.get(position).getP_name());
                            i.putExtra("p_description", Product_List.get(position).getP_description());
                            i.putExtra("p_price", Product_List.get(position).getP_price());
                            i.putExtra("p_image", Product_List.get(position).getP_image());
                            startActivity(i);
                            finish();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void add_product(View v) {
        Intent i = new Intent(getApplicationContext(), AddProduct.class);
        startActivity(i);
        finish();
    }
}