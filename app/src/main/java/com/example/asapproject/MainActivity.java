package com.example.asapproject;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {
    public static TextView status, gas;
    FirebaseDatabase database;
    DatabaseReference myRef, myReff, myRefff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gas = findViewById(R.id.gas);
        status = findViewById(R.id.status);

        database = FirebaseDatabase.getInstance();


        myRefff = database.getReference("asap");

        myRefff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gas.setText(dataSnapshot.getValue().toString());
                if (Integer.parseInt(gas.getText().toString()) <= 300){
                    status.setText("Normal");
                }else if (Integer.parseInt(gas.getText().toString()) >= 300 && Integer.parseInt(gas.getText().toString()) <= 500){
                    status.setText("Sedang");
                }else {
                    status.setText("Bahaya");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
