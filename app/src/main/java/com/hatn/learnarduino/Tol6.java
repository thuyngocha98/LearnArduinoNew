package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tol6 extends AppCompatActivity {

    private static final String TAG = "Basic_log";
    TextView Tol6_1, Tol6_2, Tol6_3, Tol6_4;
    private CardView Button1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol6);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Tol6_1=findViewById(R.id.textview_Tol6_1);
        Tol6_2=findViewById(R.id.textview_Tol6_2);
        Tol6_3=findViewById(R.id.textview_Tol6_3);
        Tol6_4=findViewById(R.id.textview_Tol6_4);

//        Button1=(CardView)findViewById(R.id.btn_Tol5_1);
//        Button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Tol5.this, Tol2_Lesson_Content.class);
//                startActivity(i);
//            }
//        });

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        DatabaseReference Tol6_lesson1_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol6").child("Lesson1").child("Name");
        DatabaseReference Tol6_lesson2_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol6").child("Lesson2").child("Name");
        DatabaseReference Tol6_lesson3_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol6").child("Lesson3").child("Name");
        DatabaseReference Tol6_lesson4_name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol6").child("Lesson4").child("Name");
        //Query Tol1name = getTol1.orderByChild("Name").limitToFirst(1);

        Tol6_lesson1_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 1 is: " + value);

                Tol6_1.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol6_lesson2_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 2 is: " + value);

                Tol6_2.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol6_lesson3_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 3 is: " + value);

                Tol6_3.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol6_lesson4_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value 4 is: " + value);

                Tol6_4.setText(value);
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
