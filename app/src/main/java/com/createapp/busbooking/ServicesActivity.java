package com.createapp.busbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServicesActivity extends AppCompatActivity {
    String busUid;
    AppCompatButton viewSitsBtn;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        busUid = getIntent().getStringExtra("busId");
        viewSitsBtn = findViewById(R.id.viewSitsBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        viewSitsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesActivity.this,SetActivity.class);
                intent.putExtra("busUid", busUid);
                startActivity(intent);
            }
        });



    }
}