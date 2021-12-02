package com.createapp.busbooking.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.createapp.busbooking.R;
import com.createapp.busbooking.RoomDB.SelectedSets;
import com.createapp.busbooking.RoomDB.SelectedSetsDatabase;

import java.util.ArrayList;
import java.util.List;

public class SetPlanViewHolder extends RecyclerView.ViewHolder {
    public ToggleButton toggleButton;

    public SetPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        toggleButton = itemView.findViewById(R.id.toggleButton);


    }

    public void setDataIntoLocalDatabase(Context context, List<String>setListThatUserSelectRightNow){
                    for (String set: setListThatUserSelectRightNow){
                    SelectedSetsDatabase.getDatabase(context).selectSetListDAO().createList(new SelectedSets(set));
                }
    }
}
