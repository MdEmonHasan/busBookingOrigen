package com.createapp.busbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.createapp.busbooking.RoomDB.SelectedSets;
import com.createapp.busbooking.RoomDB.SelectedSetsDatabase;

import com.createapp.busbooking.model.BookedSetInfo;
import com.createapp.busbooking.model.BusInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    Button demo_book_btn;
    List<SelectedSets> selectedSetsList;
    List<String> bookedSetInfoList;
    DatabaseReference databaseReference;
    String buyerId, busUid,setNames;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        busUid = getIntent().getStringExtra("busUid");

        demo_book_btn = findViewById(R.id.demo_book_btn);
        selectedSetsList = new ArrayList<>();
        bookedSetInfoList = new ArrayList<>();



        databaseReference = FirebaseDatabase.getInstance().getReference();
        for (SelectedSets selectedSets: SelectedSetsDatabase.getDatabase(PaymentActivity.this).selectSetListDAO().selectedSetList()){
            selectedSetsList.add(selectedSets);
        }

        demo_book_btn.setOnClickListener(v -> {
            buyerId = databaseReference.push().getKey();
            assert buyerId != null;
            dataSetOnDatabaseDeleteFromDAO(buyerId);


        });

        /*Get Paymentable price Start*/
        databaseReference.child("BusInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if (dataSnapshot.getValue().equals(busUid)){
                        for (DataSnapshot finalSnapshot: dataSnapshot.getChildren()){
                            BusInfo busInfo = finalSnapshot.getValue(BusInfo.class);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*Get Paymentable price End*/


    }

    private void dataSetOnDatabaseDeleteFromDAO(String buyerId) {

        databaseReference = FirebaseDatabase.getInstance().getReference("BookedSets");
        for(int i = 0; i < selectedSetsList.size(); i++)
        {

            setNames = (selectedSetsList.get(i).getSetList());
            bookedSetInfoList.add(setNames);
        }
        BookedSetInfo bookedSetInfo = new BookedSetInfo(bookedSetInfoList);


        databaseReference.child(busUid).child(buyerId).setValue(bookedSetInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(PaymentActivity.this,"DataBaseCreated",Toast.LENGTH_SHORT).show();
                    SelectedSetsDatabase.getDatabase(PaymentActivity.this).selectSetListDAO().deleteAll();
                selectedSetsList.clear();
                bookedSetInfoList.clear();
            }
        });
    }


}