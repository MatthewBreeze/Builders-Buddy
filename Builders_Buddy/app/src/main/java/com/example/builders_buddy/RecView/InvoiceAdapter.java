package com.example.builders_buddy.RecView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.builders_buddy.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.EventHolder>{
    private ArrayList<InvoiceRecView> mExampleList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onIteamClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        clickListener = listener;
    }

    public static class EventHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView secondTitle;
        public TextView ThirdTile;



        public EventHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title  = itemView.findViewById(R.id.textViewTitle);
            secondTitle = itemView.findViewById(R.id.eventLocation);
            ThirdTile = itemView.findViewById(R.id.phoneNumber);

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
    public InvoiceAdapter(ArrayList<InvoiceRecView> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view_layout,parent,false);
        EventHolder evh = new EventHolder(v, clickListener);
        return evh;


    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        InvoiceRecView currentItem = mExampleList.get(position);

        holder.title.setText(currentItem.getTitle1());
        holder.secondTitle.setText(currentItem.getTitle2());
        holder.ThirdTile.setText(currentItem.getTitle3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
