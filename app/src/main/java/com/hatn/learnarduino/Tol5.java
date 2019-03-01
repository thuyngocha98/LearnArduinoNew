package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Tol2.Lesson1.Lesson1Content;

public class Tol5 extends AppCompatActivity {

    private static final String TAG = "Basic_log";
    TextView Tol5_1, Tol5_2, Tol5_3, Tol5_4;
    private CardView Button1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol5);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Tol5_1=findViewById(R.id.textview_Tol5_1);
        Tol5_2=findViewById(R.id.textview_Tol5_2);
        Tol5_3=findViewById(R.id.textview_Tol5_3);
        Tol5_4=findViewById(R.id.textview_Tol5_4);

//        Button1=(CardView)findViewById(R.id.btn_Tol5_1);
//        Button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Tol5.this, Lesson1Content.class);
//                startActivity(i);
//            }
//        });

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        DatabaseReference Tol5_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol5").child("Lesson1").child("Name");
        DatabaseReference Tol5_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol5").child("Lesson2").child("Name");
        DatabaseReference Tol5_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol5").child("Lesson3").child("Name");
        DatabaseReference Tol5_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol5").child("Lesson4").child("Name");
        //Query Tol1name = getTol1.orderByChild("Name").limitToFirst(1);

        Tol5_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                Tol5_1.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol5_lesson2_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 2 is: " + value);

                Tol5_2.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol5_lesson3_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 3 is: " + value);

                Tol5_3.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol5_lesson4_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 4 is: " + value);

                Tol5_4.setText(value);
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
