package com.example.builders_buddy.RecView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.builders_buddy.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaxAdapter extends RecyclerView.Adapter<TaxAdapter.TradeCardHolder>{
    private ArrayList<TaxRecView> mExampleList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onIteamClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        clickListener = listener;
    }

    public static class TradeCardHolder extends RecyclerView.ViewHolder{
        public TextView textViewitle;
        public TextView Location;
        public TextView phoneNumber;



        public TradeCardHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewitle  = itemView.findViewById(R.id.textViewTitle);
            Location = itemView.findViewById(R.id.eventLocation);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onIteamClick(position);
                        }
                    }
                }
            });

        }
    }
    public TaxAdapter(ArrayList<TaxRecView> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public TradeCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_layout,parent,false);
        TradeCardHolder evh = new TradeCardHolder(v, clickListener);
        return evh;


    }

    @Override
    public void onBindViewHolder(@NonNull TradeCardHolder holder, int position) {
        TaxRecView currentItem = mExampleList.get(position);
        holder.textViewitle.setText(currentItem.getTitle());
        holder.Location.setText(currentItem.getTotal());
        holder.phoneNumber.setText("");
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
