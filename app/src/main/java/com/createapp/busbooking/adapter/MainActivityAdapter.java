package com.createapp.busbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.createapp.busbooking.R;
import com.createapp.busbooking.ServicesActivity;
import com.createapp.busbooking.model.BusInfo;
import com.createapp.busbooking.viewHolder.LandingCustomHolder;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<LandingCustomHolder> {
    List<BusInfo> busInfoList;
    Context context;

    public MainActivityAdapter(List<BusInfo> busInfoList, Context context) {
        this.busInfoList = busInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public LandingCustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.landing_custom,parent,false);
        return new LandingCustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandingCustomHolder holder, int position) {
        BusInfo busInfo = busInfoList.get(position);

        holder.busName.setText(busInfo.getBusName());
        holder.busFrom.setText(busInfo.getBusStartLocation());
        holder.busDestination.setText(busInfo.getBusDestination());
        Glide.with(context).load(busInfo.getBusImage()).into(holder.busImage);
//        holder.busImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickSingleLayoutItem();
//            }
//        });
        holder.mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ServicesActivity.class);
                intent.putExtra("busId", busInfo.getBusID());
                context.startActivity(intent);
            }
        });

    }

    private void clickSingleLayoutItem() {
        Intent intent = new Intent(context, ServicesActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return busInfoList.size();
    }
}
