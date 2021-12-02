package com.createapp.busbooking.adapter;

import android.content.Context;
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

import com.createapp.busbooking.R;
import com.createapp.busbooking.model.BusInfo;
import com.createapp.busbooking.model.BusSetPlan;
import com.createapp.busbooking.viewHolder.SetPlanViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SetPlanAdapter extends RecyclerView.Adapter<SetPlanViewHolder> {

    Context context;
    List<BusSetPlan> busSetPlanList;
    List<String> confirmSetList;
    Button confirmBtn;
    int listIndex;
    public SetPlanAdapter(Context context, List<BusSetPlan> busSetPlanList, Button confirmBtn) {
        this.context = context;
        this.busSetPlanList = busSetPlanList;
        this.confirmBtn = confirmBtn;
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
        holder.toggleButton.setTextOn(busSetPlan.getSetName());



        holder.toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int color=((ColorDrawable)holder.toggleButton.getBackground()).getColor();
            if (isChecked) {
                if(color==Color.argb(255, 252, 82, 3)){
                    Toast.makeText(context,"This sit already booked",Toast.LENGTH_LONG).show();
                }
                else{
                    holder.toggleButton.setBackgroundColor(Color.argb(255, 155, 255, 95));
                    confirmBtn.setVisibility(View.VISIBLE);
                    confirmSetList.add(holder.toggleButton.getTextOn().toString());

                }


            } else {

                if(color==Color.argb(255, 252, 82, 3)){
                    Toast.makeText(context,"This sit already booked",Toast.LENGTH_LONG).show();

                }
                else{
                    listIndex = confirmSetList.indexOf(holder.toggleButton.getTextOn().toString());
                    confirmSetList.remove(listIndex);
                    holder.toggleButton.setBackgroundColor(Color.argb(255, 0, 0, 0));


                }


            }
        });

        confirmBtn.setOnClickListener(v -> {
            Log.i("TAG", "onBindViewHolder: "+confirmSetList);
            holder.setDataIntoLocalDatabase(context,confirmSetList);
        });


    }

    @Override
    public int getItemCount() {


        return busSetPlanList.size();
    }
}
