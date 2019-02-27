package com.hatn.learnarduino;

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

public class Basic extends AppCompatActivity {

    private static final String TAG = "QuangfB";
    TextView Basic1, Basic2, Basic3, Basic4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Basic1=findViewById(R.id.textview_Basic1);
        Basic2=findViewById(R.id.textview_Basic2);
        Basic3=findViewById(R.id.textview_Basic3);
        Basic4=findViewById(R.id.textview_Basic4);

        DatabaseReference Tol1_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson1").child("Name");
        DatabaseReference Tol1_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson2").child("Name");
        DatabaseReference Tol1_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson3").child("Name");
        DatabaseReference Tol1_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson4").child("Name");
        //Query Tol1name = getTol1.orderByChild("Name").limitToFirst(1);

        Tol1_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                Basic1.setText(value);

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

                Basic2.setText(value);

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

                Basic3.setText(value);

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

                Basic4.setText(value);

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
