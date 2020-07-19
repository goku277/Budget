package com.example.budget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.MyViewHolder> {


    Context context;
    ArrayList items, price, Hashcode;
    RecyclerViewClickListener recyclerViewClickListener;

    CustomArrayAdapter(Context context, ArrayList items, ArrayList price, ArrayList Hashcode, RecyclerViewClickListener recyclerViewClickListener) {
        this.context= context;
        this.items= items;
        this.price= price;
        this.Hashcode= Hashcode;
        this.recyclerViewClickListener= recyclerViewClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.myraw, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.items1.setText(String.valueOf(items.get(position)));
        holder.price1.setText(String.valueOf(price.get(position)));

       // System.out.println("holder"+ " " + holder.getAdapterPosition());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView items1, price1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            items1=(TextView) itemView.findViewById(R.id.items_id);
            price1=(TextView) itemView.findViewById(R.id.prices_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickListener.recyclerViewListClicked(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerViewClickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}