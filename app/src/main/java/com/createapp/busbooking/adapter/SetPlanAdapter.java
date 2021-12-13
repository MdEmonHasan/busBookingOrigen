package com.createapp.busbooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.createapp.busbooking.PaymentActivity;
import com.createapp.busbooking.R;
import com.createapp.busbooking.model.BusInfo;
import com.createapp.busbooking.model.BusSetPlan;
import com.createapp.busbooking.viewHolder.SetPlanViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SetPlanAdapter extends RecyclerView.Adapter<SetPlanViewHolder> {

    Context context;
    List<BusSetPlan> busSetPlanList,busSetPlanList2;
    List<String> confirmSetList;
    Button confirmBtn;
    String busUid;

    public SetPlanAdapter(Context context, List<BusSetPlan> busSetPlanList, List<BusSetPlan> busSetPlanList2, Button confirmBtn, String busUid) {
        this.context = context;
        this.busSetPlanList = busSetPlanList;
        this.busSetPlanList2 = busSetPlanList2;
        this.confirmBtn = confirmBtn;
        this.busUid = busUid;
    }

    @NonNull
    @Override
    public SetPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.set_plan,parent,false);
        return new SetPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetPlanViewHolder holder, int position) {
        confirmSetList = new ArrayList<>();
        BusSetPlan busSetPlan = busSetPlanList.get(position);
        BusSetPlan busSetPlan2 = busSetPlanList2.get(position);


        holder.toggleButton.setTextOn(busSetPlan.getSetName());
        holder.toggleButton2.setTextOn(busSetPlan2.getSetName());

        holder.getSetThatAlreadyBooked(busUid,holder.toggleButton);
        holder.getSetThatAlreadyBooked(busUid,holder.toggleButton2);




        holder.toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int color=((ColorDrawable)holder.toggleButton.getBackground()).getColor();
            if (isChecked) {
                holder.ifButtonChecked(holder.toggleButton,color,context,confirmBtn,confirmSetList);


            } else {
                holder.ifButtonNotChecked(holder.toggleButton,color,context,confirmSetList);


            }
        });
        holder.toggleButton2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int color=((ColorDrawable)holder.toggleButton2.getBackground()).getColor();
            if (isChecked) {
                holder.ifButtonChecked(holder.toggleButton2,color,context,confirmBtn,confirmSetList);

            } else {
                holder.ifButtonNotChecked(holder.toggleButton2,color,context,confirmSetList);
            }
        });

        confirmBtn.setOnClickListener(v -> {
            holder.setDataIntoLocalDatabase(context,confirmSetList);
            Intent intent = new Intent(context,PaymentActivity.class);
            intent.putExtra("busUid",busUid);
            context.startActivity(intent);



        });


    }

    @Override
    public int getItemCount() {


        return busSetPlanList.size();
    }
}
