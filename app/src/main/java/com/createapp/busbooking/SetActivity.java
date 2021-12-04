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
//    ToggleButton a1,a2,b1,b2,c1,c2;
    Button confirmBtn;
    List<String> confirmSetList;
    String busUid;
    List<BookedSetInfo> bookedSetInfoList;
    List<BusSetPlan> busSetPlanList;
    List<BusSetPlan> busSetPlanList2;

    int listIndex;
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
        /*Get the length of set End*/
          /*  databaseReference.child("BookedSets").child(busUid).addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if (snapshot != null){

                     for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                         BookedSetInfo bookedSetInfo = dataSnapshot.getValue(BookedSetInfo.class);
                         //Log.i("TaG", "onDataChange: "+ dataSnapshot);



                         for(int i = 0; i < bookedSetInfo.getSetName().size(); i++) {
                             Log.i("fromSet", bookedSetInfo.getSetName().get(i));


//                             if (binding.a1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))) {
//                                 stopUserToSelectBookedSet(binding.a1);
//                             } else if (binding.a2.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))) {
//                                 stopUserToSelectBookedSet(binding.a2);
//                             } else if (binding.b1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))) {
//                                 stopUserToSelectBookedSet(binding.b1);
//                             } else if (binding.c1.getTextOn().toString().equals(bookedSetInfo.getSetName().get(i))) {
//                                 stopUserToSelectBookedSet(binding.c1);
//                             }
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