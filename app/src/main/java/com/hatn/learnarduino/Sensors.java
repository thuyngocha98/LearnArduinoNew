package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Tol2.Lesson1.Lesson1Content;

public class Sensors extends AppCompatActivity {

    private static final String TAG = "Sensor_log";
    private TextView Sensor1, Sensor2, Sensor3, Sensor4;
    private CardView Button1;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Sensor1=findViewById(R.id.textview_Sensor1);
        Sensor2=findViewById(R.id.textview_Sensor2);
        Sensor3=findViewById(R.id.textview_Sensor3);
        Sensor4=findViewById(R.id.textview_Sensor4);

        Button1=(CardView)findViewById(R.id.btn_Sensor1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sensors.this,Lesson1Content.class);
                startActivity(i);
            }
        });

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        DatabaseReference Tol1_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Name");
        DatabaseReference Tol1_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Name");
        DatabaseReference Tol1_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Name");
        DatabaseReference Tol1_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Name");
        //Query Tol1name = getTol1.orderByChild("Name").limitToFirst(1);

        Tol1_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                Sensor1.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol1_lesson2_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 2 is: " + value);

                Sensor2.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol1_lesson3_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 3 is: " + value);

                Sensor3.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol1_lesson4_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 4 is: " + value);

                Sensor4.setText(value);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}