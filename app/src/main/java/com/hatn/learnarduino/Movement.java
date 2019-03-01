package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Movement extends AppCompatActivity {

    private static final String TAG = "Movement_log";
    TextView movement1, movement2, movement3, movement4;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        movement1=findViewById(R.id.textview_movement1);
        movement2=findViewById(R.id.textview_movement2);
        movement3=findViewById(R.id.textview_movement3);
        movement4=findViewById(R.id.textview_movement4);



        DatabaseReference Tol4_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson1").child("Name");
        DatabaseReference Tol4_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Name");
        DatabaseReference Tol4_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Name");
        DatabaseReference Tol4_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson4").child("Name");

        Tol4_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                movement1.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol4_lesson2_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 2 is: " + value);

                movement2.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol4_lesson3_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 3 is: " + value);

                movement3.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol4_lesson4_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 4 is: " + value);

                movement4.setText(value);
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