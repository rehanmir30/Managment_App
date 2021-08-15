package com.example.managmentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.managmentapp.Model.Sold;

import java.util.ArrayList;

public class ExampleAdapter1 extends RecyclerView.Adapter<ExampleAdapter1.ExampleViewHolder> {
    private ArrayList<Sold> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView sale_name;
        public TextView sale_price;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            sale_name = itemView.findViewById(R.id.sale_name);
            sale_price = itemView.findViewById(R.id.sale_price);


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

    public ExampleAdapter1(ArrayList<Sold> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_layout, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Sold currentItem = mExampleList.get(position);


        holder.sale_name.setText(currentItem.getS_name());
        holder.sale_price.setText(currentItem.getS_price());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

