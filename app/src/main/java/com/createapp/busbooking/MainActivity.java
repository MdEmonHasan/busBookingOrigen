package com.createapp.busbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.createapp.busbooking.adapter.MainActivityAdapter;
import com.createapp.busbooking.databinding.ActivityMainBinding;
import com.createapp.busbooking.databinding.ActivitySetBinding;
import com.createapp.busbooking.model.BusInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mainRecycleView;
    List<BusInfo> busInfoList;

    DatabaseReference databaseReference;

    MainActivityAdapter mainActivityAdapter;
    String [] busFrom,busTo;
    ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainRecycleView = findViewById(R.id.mainRecycleView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        mainRecycleView.setLayoutManager(gridLayoutManager);


        busInfoList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();



        databaseReference.child("BusInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    busInfoList.clear();

                    //Log.i("TAG", "onDataChange: "+ dataSnapshot);

                   for (DataSnapshot ultimateSnapshot: dataSnapshot.getChildren()){
                       BusInfo busInfo = ultimateSnapshot.getValue(BusInfo.class);
                        busInfoList.add(busInfo);


                       busFrom = new String[busInfoList.size()];
                       busTo = new String[busInfoList.size()];

                       for (int i = 0;i<busFrom.length;i++){
                           busFrom[i] = busInfoList.get(i).getBusStartLocation();
                           busTo[i] = busInfoList.get(i).getBusDestination();
                       }

                       ArrayAdapter<String> searchArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,busFrom);
                       ArrayAdapter<String> searchArrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,busTo);
                       binding.from.setAdapter(searchArrayAdapter);
                       binding.to.setAdapter(searchArrayAdapter2);

                   }

                }


                mainActivityAdapter = new MainActivityAdapter(busInfoList,MainActivity.this);
                mainRecycleView.setAdapter(mainActivityAdapter);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", error.toString());
            }
        });
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserInput = binding.from.getText().toString().toLowerCase();

                processSearch(getUserInput);
                Toast.makeText(MainActivity.this,"search on process.....",Toast.LENGTH_LONG).show();
            }



        });


    }
    private void processSearch(String getUserInput) {
        List<BusInfo> newBusInfoList;
        newBusInfoList = new ArrayList<>();
                    for (BusInfo busInfo: busInfoList){
                        if (busInfo.getBusStartLocation().toLowerCase().equals(getUserInput)){
                            newBusInfoList.add(
                                    new BusInfo(busInfo.getBusID(),busInfo.getBusName(),
                                            busInfo.getBusStartLocation(),busInfo.getBusDestination(),
                                            busInfo.getBusImage(),busInfo.getBusPrice(),
                                            busInfo.getNumberOfSets()));


                        }


                }
        MainActivityAdapter darkAdapter = new MainActivityAdapter(newBusInfoList,MainActivity.this);
        mainRecycleView.setAdapter(darkAdapter);

    }
}