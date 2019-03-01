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

import java.util.HashMap;
import java.util.Map;

public class LED extends AppCompatActivity {

    private static final String TAG = "LED_log";
    TextView tvLED1, tvLED2, tvLED3, tvLED4;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        tvLED1=findViewById(R.id.textview_LED1);
        tvLED2=findViewById(R.id.textview_LED2);
        tvLED3=findViewById(R.id.textview_LED3);
        tvLED4=findViewById(R.id.textview_LED4);

        DatabaseReference Tol1_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Name");
        DatabaseReference Tol1_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Name");
        DatabaseReference Tol1_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Name");
        DatabaseReference Tol1_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Name");
        //Query Tol1name = getTol1.orderByChild("Name").limitToFirst(1);

        Tol1_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                tvLED1.setText(value);

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

                tvLED2.setText(value);

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

                tvLED3.setText(value);

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

                tvLED4.setText(value);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


//        DatabaseReference typeoflesson2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2");
//        DatabaseReference typeoflesson3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3");
//        DatabaseReference typeoflesson4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4");
//
//
//        DatabaseReference tol2lesson1content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2");
//        DatabaseReference tol2lesson2content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3");
//        DatabaseReference tol2lesson3content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4");
//        DatabaseReference tol2lesson4content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5");
//        DatabaseReference tol2lesson5content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1");
//        DatabaseReference tol3lesson1content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson1");
//        DatabaseReference tol3lesson2content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2");
//        DatabaseReference tol3lesson3content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3");
//        DatabaseReference tol3lesson4content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson4");
//        DatabaseReference tol3lesson5content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson5");
//        DatabaseReference tol4lesson1content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1");
//        DatabaseReference tol4lesson2content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2");
//        DatabaseReference tol4lesson3content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3");
//        DatabaseReference tol4lesson4content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4");
//        DatabaseReference tol4lesson5content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5");
//        //FirebaseDatabase led1 = database.getReference("LED1");
//
//        //Type of lesson
//        Map tolMap = new HashMap();
//        tolMap.put("Name", "Basic");
//        tolMap.put("Number_of_lesson","5");
//        typeoflesson2.setValue(tolMap);
//        typeoflesson3.setValue(tolMap);
//        typeoflesson4.setValue(tolMap);
//
//        //Put things into database here
//        Map lesson1 = new HashMap();
//        lesson1.put("Name", "Introduction");
//        lesson1.put("Content","abc");
//        tol2lesson1content.setValue(lesson1);
//        tol2lesson2content.setValue(lesson1);
//        tol2lesson3content.setValue(lesson1);
//        tol2lesson4content.setValue(lesson1);
//        tol2lesson5content.setValue(lesson1);
//        tol3lesson1content.setValue(lesson1);
//        tol3lesson2content.setValue(lesson1);
//        tol3lesson3content.setValue(lesson1);
//        tol3lesson4content.setValue(lesson1);
//        tol3lesson5content.setValue(lesson1);
//        tol4lesson1content.setValue(lesson1);
//        tol4lesson2content.setValue(lesson1);
//        tol4lesson3content.setValue(lesson1);
//        tol4lesson4content.setValue(lesson1);
//        tol4lesson5content.setValue(lesson1);



        //myRef.setValue("Hello!");

//        typeoflesson.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//
//                Log.d(TAG, "Value is: " + value);
//                //tvLED1.setText(value);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//        lesson1content.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
