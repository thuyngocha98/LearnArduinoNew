package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Movement extends AppCompatActivity {

    private static final String TAG = "Movement_log";
    private CardView btnMovement1,btnMovement2,btnMovement3,btnMovement4,btnMovement5,btnMovement6,btnMovement7,btnMovement8;
    ProgressDialog progressDialog;
    int numberTotalContent = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mergeIdCardView();

//        btnSenser1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LED.this, Tol2_Lesson_Content.class);
//                startActivity(i);
//            }
//        });

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        // set visibility with number of lesson
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Number_of_lesson");
        number1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                //set number lesson visibilyty
                int[] CardView_List = {
                        R.id.btn_movement1 ,  R.id.btn_movement2 ,  R.id.btn_movement3 ,  R.id.btn_movement4 ,  R.id.btn_movement5 ,
                        R.id.btn_movement6 ,  R.id.btn_movement7 ,  R.id.btn_movement8 ,
                };
                // set total number lesson
                int[] numberTotalLesson = {
                        R.id.totalMovement1_lessonnumber,R.id.totalMovement2_lessonnumber,R.id.totalMovement3_lessonnumber,R.id.totalMovement4_lessonnumber,R.id.totalMovement5_lessonnumber,
                        R.id.totalMovement6_lessonnumber,R.id.totalMovement7_lessonnumber,R.id.totalMovement8_lessonnumber,
                };
                // set name of lesson
                int[] nameLesson = {
                        R.id.textview_movement1 ,  R.id.textview_movement2 ,  R.id.textview_movement3 ,  R.id.textview_movement4 ,  R.id.textview_movement5 ,
                        R.id.textview_movement6 ,  R.id.textview_movement7 ,  R.id.textview_movement8 ,
                };


                //set number lesson visibilyty
                for(int i = value; i < numberTotalContent; i++){
                    CardView temp = findViewById(CardView_List[i]);
                    temp.setVisibility(View.GONE);
                }
                // set total number lesson
                for(int i = 0; i < value; i++){
                    TextView tvTemp = findViewById(numberTotalLesson[i]);
                    tvTemp.setText(value+"");
                }
                for(int i = 0; i < value; i++){
                    String lessonTemp = "Lesson"+(i+1);
                    DatabaseReference dataTemp = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child(lessonTemp).child("Name");
                    TextView textViewTemp = findViewById(nameLesson[i]);
                    Function function = new Function();
                    function.SetDataIntoObject(dataTemp, textViewTemp);
                }
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

    private void mergeIdCardView(){
        btnMovement1 = findViewById(R.id.btn_movement1);
        btnMovement2 = findViewById(R.id.btn_movement2);
        btnMovement3 = findViewById(R.id.btn_movement3);
        btnMovement4 = findViewById(R.id.btn_movement4);
        btnMovement5 = findViewById(R.id.btn_movement5);
        btnMovement6 = findViewById(R.id.btn_movement6);
        btnMovement7 = findViewById(R.id.btn_movement7);
        btnMovement8 = findViewById(R.id.btn_movement8);
    }
}