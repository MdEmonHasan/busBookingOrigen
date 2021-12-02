package com.createapp.busbooking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.createapp.busbooking.RoomDB.SelectedSets;
import com.createapp.busbooking.RoomDB.SelectedSetsDatabase;
import com.createapp.busbooking.adapter.MainActivityAdapter;
import com.createapp.busbooking.adapter.SetPlanAdapter;
import com.createapp.busbooking.databinding.ActivitySetBinding;
import com.createapp.busbooking.model.BookedSetInfo;
import com.createapp.busbooking.model.BusInfo;
import com.createapp.busbooking.model.BusSetPlan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SetActivity extends AppCompatActivity {
//    ToggleButton a1,a2,b1,b2,c1,c2;
    Button confirmBtn,confirmBtn2;
    List<String> confirmSetList;
    String busUid;
    List<BookedSetInfo> bookedSetInfoList;
    List<BusSetPlan> busSetPlanList;
    List<BusSetPlan> busSetPlanList2;

    int listIndex;
    int leftSideSetLength;
    int rightSideSetLength;
    DatabaseReference databaseReference;
    RecyclerView rightSideSetPlanRecycleView;
    RecyclerView leftSideSetPlanRecycleView;
    ActivitySetBinding binding;
    SetPlanAdapter setPlanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        binding = ActivitySetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        rightSideSetPlanRecycleView = findViewById(R.id.rightSideSetPlanRecycleView);
        leftSideSetPlanRecycleView = findViewById(R.id.leftSideSetPlanRecycleView);



        String[] rightSideSetNumber = {"a3", "a4", "b3","b4","c3","c4","d3","d4","e3","e4","f3","f4","g3","g4","h3","h4","i3","i4","j3","j4","k3","k4"};
        String[] leftSideSetNumber = {"a1", "a2", "b1","b2","c1","c2","d1","d2","e1","e2","f1","f2","g1","g2","h1","h2","i1","i2","j1","j2","k1","k2"};

        busUid = getIntent().getStringExtra("busUid");

        confirmBtn = findViewById(R.id.confirmBtn);
        confirmBtn2 = findViewById(R.id.confirmBtn2);
        confirmSetList = new ArrayList<>();
        bookedSetInfoList = new ArrayList<>();
        busSetPlanList = new ArrayList<>();
        busSetPlanList2 = new ArrayList<>();



        databaseReference = FirebaseDatabase.getInstance().getReference();

        /*Get the length of set start*/
        databaseReference.child("BusInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    for (DataSnapshot lastSnapshot: dataSnapshot.getChildren()){
                        if (lastSnapshot.getKey().equals(busUid)){
                            BusInfo busInfo = lastSnapshot.getValue(BusInfo.class);
                            rightSideSetLength = Integer.parseInt(busInfo.getNumberOfSetInRightSide());
                            leftSideSetLength = Integer.parseInt(busInfo.getNumberOfSetInLeftSide());


                        }
                    }

                }


                for(int i= 0; i<rightSideSetLength;i++){
                    BusSetPlan busSetPlan = new BusSetPlan(rightSideSetNumber[i]);
                    busSetPlanList.add(busSetPlan);


                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SetActivity.this,2);
                rightSideSetPlanRecycleView.setLayoutManager(gridLayoutManager);

                setPlanAdapter = new SetPlanAdapter(SetActivity.this, busSetPlanList,confirmBtn);
                rightSideSetPlanRecycleView.setAdapter(setPlanAdapter);


                for(int i= 0; i<leftSideSetLength;i++){

                    BusSetPlan busSetPlan = new BusSetPlan(leftSideSetNumber[i]);
                    busSetPlanList2.add(busSetPlan);


                }
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(SetActivity.this,2);
                leftSideSetPlanRecycleView.setLayoutManager(gridLayoutManager2);

                setPlanAdapter = new SetPlanAdapter(SetActivity.this, busSetPlanList2,confirmBtn2);
                leftSideSetPlanRecycleView.setAdapter(setPlanAdapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", error.toString());
            }

        });
        /*Get the length of set End*/

/*        databaseReference.child("BookedSets").child(busUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null){

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        BookedSetInfo bookedSetInfo = dataSnapshot.getValue(BookedSetInfo.class);


                        for(int i = 0; i < bookedSetInfo.getSetName().size(); i++){


                            if (binding.a1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
                                stopUserToSelectBookedSet(binding.a1);
                            }
                            else if(binding.a2.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
                                stopUserToSelectBookedSet(binding.a2);
                            }
                            else if(binding.b1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
                                stopUserToSelectBookedSet(binding.b1);
                            }
                            else if(binding.c1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))){
                                stopUserToSelectBookedSet(binding.c1);
                            }


                        }


                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.a1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                workWithSet(binding.a1);
            } else {
                toggleClickOnSet(binding.a1);

            }
        });
        binding.a2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                workWithSet(binding.a2);
            } else {
                toggleClickOnSet(binding.a2);

            }
        });



        binding.b1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                workWithSet(binding.b1);

            } else {
                // The toggle is disabled

                toggleClickOnSet(binding.b1);
            }
        });
        binding.c1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                workWithSet(binding.c1);

            } else {
                // The toggle is disabled

                toggleClickOnSet(binding.c1);

            }
        });
        binding.c2.setOnCheckedChangeListener((buttonView, isChecked) -> {if (isChecked) {workWithSet(binding.c2);} else {toggleClickOnSet(binding.c2);}});*/


//        confirmBtn.setOnClickListener(v -> {
//
//
//            for (String set: confirmSetList){
//                SelectedSetsDatabase.getDatabase(SetActivity.this).selectSetListDAO().createList(new SelectedSets(set));
//            }
//
//            Intent intent = new Intent(SetActivity.this,PaymentActivity.class);
//            intent.putExtra("busUid",busUid);
//            startActivity(intent);
//            finish();
//
//
//        });

    }

    private void getTheBusSetValueAndSetIntoList(){

    }

   /* private void workWithSet(ToggleButton buttonId) {
        int color=((ColorDrawable)buttonId.getBackground()).getColor();

        if(color==Color.argb(255, 252, 82, 3)){
            Toast.makeText(SetActivity.this,"This sit already booked",Toast.LENGTH_LONG).show();
        }
        else{
            buttonId.setBackgroundColor(Color.argb(255, 155, 255, 95));
            confirmBtn.setVisibility(View.VISIBLE);
            confirmSetList.add(buttonId.getTextOn().toString());
        }



    }

    private void toggleClickOnSet(ToggleButton buttonId){
        int color=((ColorDrawable)buttonId.getBackground()).getColor();
        if(color==Color.argb(255, 252, 82, 3)){
            Toast.makeText(SetActivity.this,"This sit already booked",Toast.LENGTH_LONG).show();

        }
        else{
            listIndex = confirmSetList.indexOf(buttonId.getTextOn().toString());
            confirmSetList.remove(listIndex);
            buttonId.setBackgroundColor(Color.argb(255, 0, 0, 0));

        }

    }
    private void stopUserToSelectBookedSet(ToggleButton buttonId){
        buttonId.setBackgroundColor(Color.argb(255, 252, 82, 3));
    }*/

}