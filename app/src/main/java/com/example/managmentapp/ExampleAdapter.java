package com.example.managmentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.managmentapp.Model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Product> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_image;
        public TextView product_name;
        public TextView product_price;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<Product> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Product currentItem = mExampleList.get(position);

        //holder.product_image.setImageResource(currentItem.());
        holder.product_name.setText(currentItem.getP_name());
        holder.product_price.setText(currentItem.getP_price());

        String imageUri="https://firebasestorage.googleapis.com/v0/b/managment-app-b9d30.appspot.com/o/images%2F"+currentItem.getP_image()+"?alt=media&token=3827a434-9e70-4e86-9fac-7848e598f469";

        Picasso.get().load(imageUri).into(holder.product_image);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
