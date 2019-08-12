package com.example.arizki.skripsiarizki;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {
    ArrayList<Sensor> listSensor;
    Context context;

    SensorAdapter (Context context){
        this.context = context;
    }

    public ArrayList<Sensor>getListSensor(){return listSensor;}

    public void setListSensor(ArrayList<Sensor> listSensor){

        this.listSensor = listSensor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kekeruhan_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull SensorAdapter.ViewHolder viewHolder, int i){
        viewHolder.NTU.setText(getListSensor().get(i).getNTU());
        viewHolder.Kg.setText(getListSensor().get(i).getKg());
        viewHolder.status.setText(getListSensor().get(i).getstatus());
    }
    @Override
    public int getItemCount(){
        return getListSensor().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hasilNTU)
        TextView NTU;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.hasilKg)
        TextView Kg;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
