package com.example.managmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managmentapp.Model.Purchase;
import com.example.managmentapp.Model.Sold;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Purchases_Screen extends AppCompatActivity {

    private ArrayList<Purchase> Purchase_List;
    private RecyclerView mRecyclerView;
    private ExampleAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases_screen);

        mRecyclerView = findViewById(R.id.recyclerView);
        Purchase_List = new ArrayList<>();

        String dataBase_url = "https://managment-app-b9d30-default-rtdb.firebaseio.com/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(dataBase_url);
        DatabaseReference myRef = database.getReference("Purchases");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total_products = (int) dataSnapshot.getChildrenCount();
                if (total_products < 1) {
                    Toast.makeText(getApplicationContext(), "No Purchased items to show", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Tap any item to check its purchase time", Toast.LENGTH_LONG).show();

                    for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {

                        if (dataSnapshot.child(String.valueOf(i)).exists()) {
                            String naam = (String) dataSnapshot.child(String.valueOf(i)).child("Name").getValue();
                            String keemat = (String) dataSnapshot.child(String.valueOf(i)).child("Price").getValue();
                            String mikdar = (String) dataSnapshot.child(String.valueOf(i)).child("Quantity").getValue();
                            String wakt = (String) dataSnapshot.child(String.valueOf(i)).child("Time").getValue();

                            Purchase purchase = new Purchase(naam, wakt, mikdar);
                            Purchase_List.add(purchase);

                        }

                    }

                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(Purchases_Screen.this, LinearLayoutManager.VERTICAL, false);
                    mAdapter = new ExampleAdapter2(Purchase_List);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new ExampleAdapter2.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                            Toast.makeText(getApplicationContext(), Purchase_List.get(position).getTime(), Toast.LENGTH_LONG).show();
//                            Intent i = new Intent(getApplicationContext(), OpenSold.class);
//                            i.putExtra("s_name", Purchase_List.get(position).getS_name());
//                            i.putExtra("s_quantity", Purchase_List.get(position).getS_quantity());
//                            i.putExtra("s_price", Sold_List.get(position).getS_price());
//                            i.putExtra("s_time", Sold_List.get(position).getS_time());
//                            startActivity(i);
//                            finish();
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

    public void add_purchases(View v) {
        Intent i = new Intent(getApplicationContext(), AddPurchases.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}