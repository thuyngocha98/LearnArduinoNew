package com.hatn.learnarduino;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LED extends AppCompatActivity {

    private static final String TAG = "Quang";
    TextView tvLED1, tvLED2, tvLED3, tvLED4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvLED1=findViewById(R.id.textview_LED1);

        //DatabaseReference typeoflesson = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1");
        DatabaseReference lesson2content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson2");
        DatabaseReference lesson3content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson3");
        DatabaseReference lesson4content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson4");
        DatabaseReference lesson5content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson5");
        //FirebaseDatabase led1 = database.getReference("LED1");

        //Type of lesson
//        Map tolMap = new HashMap();
//        tolMap.put("Name", "Basic");
//        tolMap.put("Number_of_lesson","5");
//        typeoflesson.setValue(tolMap);

        //Put things into database here
//        Map lesson1 = new HashMap();
//        lesson1.put("Name", "Introduction");
//        lesson1.put("Content","abc");
//        lesson2content.setValue(lesson1);
//        lesson3content.setValue(lesson1);
//        lesson4content.setValue(lesson1);
//        lesson5content.setValue(lesson1);


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
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
