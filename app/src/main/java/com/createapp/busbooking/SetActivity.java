package com.createapp.busbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
    Button confirmBtn;
    List<String> confirmSetList;
    String busUid,setPrice;
    List<BookedSetInfo> bookedSetInfoList;
    List<BusSetPlan> busSetPlanList;
    List<BusSetPlan> busSetPlanList2;
    int SetLength;
    DatabaseReference databaseReference;
    RecyclerView rightSideSetPlanRecycleView;

    ActivitySetBinding binding;
    SetPlanAdapter setPlanAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        binding = ActivitySetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        rightSideSetPlanRecycleView = findViewById(R.id.rightSideSetPlanRecycleView);

        String[] rightSideSetNumber = {"a3", "a4", "b3","b4","c3",
                "c4","d3","d4","e3","e4","f3","f4","g3","g4"
                ,"h3","h4","i3","i4","j3","j4","k3","k4"};
        String[] leftSideSetNumber = {"a1", "a2","b1","b2","c1","c2","d1","d2","e1","e2","f1","f2","g1","g2"
                ,"h1","h2","i1","i2","i3","i4","j1","j2","j3","j4","k1","k2","k3","k4"};



        busUid = getIntent().getStringExtra("busUid");

        confirmBtn = findViewById(R.id.confirmBtn);

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
                            SetLength = Integer.parseInt(busInfo.getNumberOfSets());
                            setPrice = busInfo.getBusPrice();


                        }
                    }

                }
                for(int i= 0; i<SetLength/2;i++){
                    BusSetPlan busSetPlan = new BusSetPlan(rightSideSetNumber[i]);
                    BusSetPlan busSetPlan2 = new BusSetPlan(leftSideSetNumber[i]);
                    busSetPlanList.add(busSetPlan);
                    busSetPlanList2.add(busSetPlan2);


                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SetActivity.this,2);
                rightSideSetPlanRecycleView.setLayoutManager(gridLayoutManager);

                setPlanAdapter = new SetPlanAdapter(SetActivity.this, busSetPlanList,busSetPlanList2,confirmBtn,busUid);
                rightSideSetPlanRecycleView.setAdapter(setPlanAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", error.toString());
            }

        });
    }

}