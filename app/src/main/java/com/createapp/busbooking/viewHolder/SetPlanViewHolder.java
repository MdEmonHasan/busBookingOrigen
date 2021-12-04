package com.createapp.busbooking.viewHolder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.createapp.busbooking.R;
import com.createapp.busbooking.RoomDB.SelectedSets;
import com.createapp.busbooking.RoomDB.SelectedSetsDatabase;
import com.createapp.busbooking.model.BookedSetInfo;
import com.createapp.busbooking.model.BusSetPlan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SetPlanViewHolder extends RecyclerView.ViewHolder {
    public ToggleButton toggleButton,toggleButton2;
    DatabaseReference databaseReference;

    public SetPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        toggleButton = itemView.findViewById(R.id.toggleButton);
        toggleButton2 = itemView.findViewById(R.id.toggleButton2);
        databaseReference = FirebaseDatabase.getInstance().getReference();




    }

    public void getSetThatAlreadyBooked(String busUid){

        databaseReference.child("BookedSets").child(busUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null){

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        BookedSetInfo bookedSetInfo = dataSnapshot.getValue(BookedSetInfo.class);



                        for(int i = 0; i < bookedSetInfo.getSetName().size(); i++){
                            Log.i("TAG", bookedSetInfo.getSetName().get(i));


//                            if (binding.a1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
//                                stopUserToSelectBookedSet(binding.a1);
//                            }
//                            else if(binding.a2.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
//                                stopUserToSelectBookedSet(binding.a2);
//                            }
//                            else if(binding.b1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
//                                stopUserToSelectBookedSet(binding.b1);
//                            }
//                            else if(binding.c1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
//                                stopUserToSelectBookedSet(binding.c1);
//                            }


                        }


                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setDataIntoLocalDatabase(Context context, List<String>setListThatUserSelectRightNow){
                    for (String set: setListThatUserSelectRightNow){
                    SelectedSetsDatabase.getDatabase(context).selectSetListDAO().createList(new SelectedSets(set));
                }
    }
    public void ifButtonChecked(ToggleButton buttonId, int color, Context context, Button confirmBtn,List<String> confirmSetList){
        if (color == Color.argb(255, 252, 82, 3)){
            Toast.makeText(context,"This sit already booked",Toast.LENGTH_LONG).show();
        }else {
            buttonId.setBackgroundColor(Color.argb(255, 155, 255, 95));
            confirmBtn.setVisibility(View.VISIBLE);
            confirmSetList.add(buttonId.getTextOn().toString());
        }


    }
    public void ifButtonNotChecked(ToggleButton buttonId, int color, Context context,
                                   List<String> confirmSetList){
        if(color==Color.argb(255, 252, 82, 3)){
            Toast.makeText(context,"This sit already booked",Toast.LENGTH_LONG).show();

        }else{
            int listIndex = confirmSetList.indexOf(buttonId.getTextOn().toString());
            confirmSetList.remove(listIndex);
            buttonId.setBackgroundColor(Color.argb(255, 0, 0, 0));
        }

    }
}
