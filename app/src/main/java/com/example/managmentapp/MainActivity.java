package com.example.managmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView product_details,sales_details,reports,purchases,about_us,help_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_details=findViewById(R.id.productDetails);
        sales_details=findViewById(R.id.saleDetails);
        reports=findViewById(R.id.reports);
        purchases=findViewById(R.id.purchases);
        about_us=findViewById(R.id.about_us);
        help_center=findViewById(R.id.help_center);
    }

   public void product_screen(View v){
       Intent i=new Intent(getApplicationContext(), Product_Details.class);
       startActivity(i);
    }
    public void sales_screen(View v){
        Intent i=new Intent(getApplicationContext(), Sales_Screen.class);
        startActivity(i);
    }
    public void reports_screen(View v){
        Intent i=new Intent(getApplicationContext(), Reports_Screen.class);
        startActivity(i);
    }
    public void purchases_screen(View v){
        Intent i=new Intent(getApplicationContext(),Purchases_Screen.class);
        startActivity(i);
    }
    public void no_display(View v){
        Toast.makeText(getApplicationContext(), "No Screen available at the moment", Toast.LENGTH_SHORT).show();
    }
}