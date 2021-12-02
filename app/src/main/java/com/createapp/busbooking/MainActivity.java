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
import android.widget.Toast;

import com.createapp.busbooking.adapter.MainActivityAdapter;
import com.createapp.busbooking.model.BusInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView searchBar;
    RecyclerView mainRecycleView;
    List<BusInfo> busInfoList;

    DatabaseReference databaseReference;
    AppCompatButton searchBtn;
    MainActivityAdapter mainActivityAdapter;
    String [] movieDoc;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBar= findViewById(R.id.searchBar);

        mainRecycleView = findViewById(R.id.mainRecycleView);
        searchBtn = findViewById(R.id.searchBtn);

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


                       movieDoc = new String[busInfoList.size()];

                       for (int i = 0;i<movieDoc.length;i++){
                           movieDoc[i] = busInfoList.get(i).getBusName();
                           Log.i("hello", busInfoList.get(i).getBusName());
                       }

                       ArrayAdapter<String> searchArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,movieDoc);
                       searchBar.setAdapter(searchArrayAdapter);






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









        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserInput = searchBar.getText().toString().toLowerCase();

                processSearch(getUserInput);
            }



        });


    }
    private void processSearch(String getUserInput) {
                    for (BusInfo busInfo: busInfoList){
                        if (busInfo.getBusName().toLowerCase().equals(getUserInput)){



                            Toast.makeText(MainActivity.this,"search on process.....",Toast.LENGTH_LONG).show();
                        }
                }

    }
}