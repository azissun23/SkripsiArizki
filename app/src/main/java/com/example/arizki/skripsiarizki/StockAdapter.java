package com.example.arizki.skripsiarizki;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    ArrayList<com.example.arizki.skripsiarizki.Pojo.StockPakan> listStok;
    Context context;

    StockAdapter (Context context){
        this.context = context;
    }

    public ArrayList<com.example.arizki.skripsiarizki.Pojo.StockPakan> getListStok(){
        return listStok;
    }

    public void setListStok(ArrayList<com.example.arizki.skripsiarizki.Pojo.StockPakan> listStok){

        this.listStok = listStok;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stock_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Kg.setText(getListStok().get(i).getKg());

    }
    @Override
    public int getItemCount() { return getListStok().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hasilKg)
        TextView Kg;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
