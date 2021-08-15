package com.example.managmentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.managmentapp.Model.Purchase;
import com.example.managmentapp.Model.Sold;

import java.util.ArrayList;

public class ExampleAdapter2 extends RecyclerView.Adapter<ExampleAdapter2.ExampleViewHolder> {
    private ArrayList<Purchase> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView purchase_name;
        public TextView purchase_quantity;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            purchase_name = itemView.findViewById(R.id.purchase_name);
            purchase_quantity = itemView.findViewById(R.id.purchase_quantity);


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

    public ExampleAdapter2(ArrayList<Purchase> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_item_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Purchase currentItem = mExampleList.get(position);


        holder.purchase_name.setText(currentItem.getName());
        holder.purchase_quantity.setText(currentItem.getQuantity());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}


