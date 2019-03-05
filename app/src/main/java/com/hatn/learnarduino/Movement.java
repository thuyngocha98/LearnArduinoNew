package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseAuth mAuth;
    Intent intent;
    int max_led;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        max_led = intent.getIntExtra("MAXLED2", 0);
        if(max_led == 0)
            max_led = intent.getIntExtra("MAXLED", 0);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                int exp=value.intValue();
                int maxled = intent.getIntExtra("MAXLED2",0);
                Log.d("abcdef", "Sensors: "+maxled);
                if(maxled == 0)
                    maxled = intent.getIntExtra("MAXLED",1);

                int[] CardViewColor_list = {
                        R.id.tol4_colorcard1 , R.id.tol4_colorcard2, R.id.tol4_colorcard3, R.id.tol4_colorcard4,
                        R.id.tol4_colorcard5 , R.id.tol4_colorcard6, R.id.tol4_colorcard7, R.id.tol4_colorcard8,
                };
                int[] CardView_List = {
                        R.id.btn_movement1 ,  R.id.btn_movement2 ,  R.id.btn_movement3 ,  R.id.btn_movement4 ,  R.id.btn_movement5 ,
                        R.id.btn_movement6 ,  R.id.btn_movement7 ,  R.id.btn_movement8 ,
                };

                for(int i =0; i <7; i++){
                    CardView temp = findViewById(CardViewColor_list[i]);
                    CardView Allcard = findViewById(CardView_List[i+1]);
                    if(exp >=5){
                        temp.setCardBackgroundColor(Color.parseColor("#ff669900"));
                        temp.setClickable(false);
                        exp -=5;
                    }
                    else
                        Allcard.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

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

}