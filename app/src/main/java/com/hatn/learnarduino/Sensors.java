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
import com.hatn.learnarduino.Tol2.Tol2_Lesson_Content;
import com.hatn.learnarduino.Tol3.Tol3_Lesson_Content;

public class Sensors extends AppCompatActivity {

    private static final String TAG = "Sensor_log";
    ProgressDialog progressDialog;
    int numberTotalContent = 26;
    FirebaseAuth mAuth;

    Intent intent;

    public static final String LESSONNUMBERINTENT ="LESSONNUMBERINTENT";
    public static final String LESSONNAME = "LESSONNAME";
    public static final String HASCOLOR = "HASCOLOR";
    TextView textViewTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent=getIntent();

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                int exp=value.intValue();
                int maxbasic = intent.getIntExtra("MAXBASIC",0);
                int expSensor = exp - maxbasic;

                int[] Cardview_color = {
                        R.id.cardview_color_sensor1,R.id.cardview_color_sensor2,R.id.cardview_color_sensor3,R.id.cardview_color_sensor4,R.id.cardview_color_sensor5,
                        R.id.cardview_color_sensor6,R.id.cardview_color_sensor7,R.id.cardview_color_sensor8,R.id.cardview_color_sensor9,R.id.cardview_color_sensor10,
                        R.id.cardview_color_sensor11,R.id.cardview_color_sensor12,R.id.cardview_color_sensor13,R.id.cardview_color_sensor14,R.id.cardview_color_sensor15,
                        R.id.cardview_color_sensor16,R.id.cardview_color_sensor17,R.id.cardview_color_sensor18,R.id.cardview_color_sensor19,R.id.cardview_color_sensor20,
                        R.id.cardview_color_sensor21,R.id.cardview_color_sensor22,R.id.cardview_color_sensor23,R.id.cardview_color_sensor24,R.id.cardview_color_sensor25,
                        R.id.cardview_color_sensor26,
                };
                int[] CardView_List = {
                        R.id.btn_Sensor1 ,  R.id.btn_Sensor2 ,  R.id.btn_Sensor3 ,  R.id.btn_Sensor4 ,  R.id.btn_Sensor5 ,
                        R.id.btn_Sensor6 ,  R.id.btn_Sensor7 ,  R.id.btn_Sensor8 ,  R.id.btn_Sensor9 ,  R.id.btn_Sensor10 ,
                        R.id.btn_Sensor11 ,  R.id.btn_Sensor12 ,  R.id.btn_Sensor13 ,  R.id.btn_Sensor14 ,  R.id.btn_Sensor15 ,
                        R.id.btn_Sensor16 ,  R.id.btn_Sensor17 ,  R.id.btn_Sensor18 ,  R.id.btn_Sensor19 ,  R.id.btn_Sensor20 ,
                        R.id.btn_Sensor21 ,  R.id.btn_Sensor22 ,  R.id.btn_Sensor23 ,  R.id.btn_Sensor24 ,  R.id.btn_Sensor25 ,  R.id.btn_Sensor26 ,
                };

                for(int i =0; i <26; i++){
                    CardView temp = findViewById(Cardview_color[i]);
                    CardView Allcard = findViewById(CardView_List[i]);
                    if(expSensor >=5){
                        temp.setCardBackgroundColor(Color.parseColor("#ff669900"));
                        temp.setClickable(false);
                        expSensor -=5;
                    }
                    //Allcard.setClickable(false);
//                    if (exp>=5)
//                    {
//                        for (i=0;i<=exp;i+=5)
//                        {
//                            CardView expcheck = findViewById(CardView_List[5/i]);
//                            expcheck.setClickable(true);
//                        }
//                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });




        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        // set visibility with number of lesson
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Number_of_lesson");
        number1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                //set number of lesson visibility
                int[] CardView_List = {
                        R.id.btn_Sensor1 ,  R.id.btn_Sensor2 ,  R.id.btn_Sensor3 ,  R.id.btn_Sensor4 ,  R.id.btn_Sensor5 ,
                        R.id.btn_Sensor6 ,  R.id.btn_Sensor7 ,  R.id.btn_Sensor8 ,  R.id.btn_Sensor9 ,  R.id.btn_Sensor10 ,
                        R.id.btn_Sensor11 ,  R.id.btn_Sensor12 ,  R.id.btn_Sensor13 ,  R.id.btn_Sensor14 ,  R.id.btn_Sensor15 ,
                        R.id.btn_Sensor16 ,  R.id.btn_Sensor17 ,  R.id.btn_Sensor18 ,  R.id.btn_Sensor19 ,  R.id.btn_Sensor20 ,
                        R.id.btn_Sensor21 ,  R.id.btn_Sensor22 ,  R.id.btn_Sensor23 ,  R.id.btn_Sensor24 ,  R.id.btn_Sensor25 ,  R.id.btn_Sensor26 ,
                };
                // set total number lesson
                int[] numberTotalLesson = {
                        R.id.totalSensor1_lessonnumber,R.id.totalSensor2_lessonnumber,R.id.totalSensor3_lessonnumber,R.id.totalSensor4_lessonnumber,R.id.totalSensor5_lessonnumber,
                        R.id.totalSensor6_lessonnumber,R.id.totalSensor7_lessonnumber,R.id.totalSensor8_lessonnumber,R.id.totalSensor9_lessonnumber,R.id.totalSensor10_lessonnumber,
                        R.id.totalSensor11_lessonnumber,R.id.totalSensor12_lessonnumber,R.id.totalSensor13_lessonnumber,R.id.totalSensor14_lessonnumber,R.id.totalSensor15_lessonnumber,
                        R.id.totalSensor16_lessonnumber,R.id.totalSensor17_lessonnumber,R.id.totalSensor18_lessonnumber,R.id.totalSensor19_lessonnumber,R.id.totalSensor20_lessonnumber,
                        R.id.totalSensor21_lessonnumber,R.id.totalSensor22_lessonnumber,R.id.totalSensor23_lessonnumber,R.id.totalSensor24_lessonnumber,R.id.totalSensor25_lessonnumber,R.id.totalSensor26_lessonnumber,

                };
                int[] Cardview_color = {
                        R.id.cardview_color_sensor1,R.id.cardview_color_sensor2,R.id.cardview_color_sensor3,R.id.cardview_color_sensor4,R.id.cardview_color_sensor5,
                        R.id.cardview_color_sensor6,R.id.cardview_color_sensor7,R.id.cardview_color_sensor8,R.id.cardview_color_sensor9,R.id.cardview_color_sensor10,
                        R.id.cardview_color_sensor11,R.id.cardview_color_sensor12,R.id.cardview_color_sensor13,R.id.cardview_color_sensor14,R.id.cardview_color_sensor15,
                        R.id.cardview_color_sensor16,R.id.cardview_color_sensor17,R.id.cardview_color_sensor18,R.id.cardview_color_sensor19,R.id.cardview_color_sensor20,
                        R.id.cardview_color_sensor21,R.id.cardview_color_sensor22,R.id.cardview_color_sensor23,R.id.cardview_color_sensor24,R.id.cardview_color_sensor25,
                        R.id.cardview_color_sensor26,
                };
                // set name of lesson
                int[] nameLesson = {
                        R.id.textview_Sensor1 ,  R.id.textview_Sensor2 ,  R.id.textview_Sensor3 ,  R.id.textview_Sensor4 ,  R.id.textview_Sensor5 ,
                        R.id.textview_Sensor6 ,  R.id.textview_Sensor7 ,  R.id.textview_Sensor8 ,  R.id.textview_Sensor9 ,  R.id.textview_Sensor10 ,
                        R.id.textview_Sensor11 ,  R.id.textview_Sensor12 ,  R.id.textview_Sensor13 ,  R.id.textview_Sensor14 ,  R.id.textview_Sensor15 ,
                        R.id.textview_Sensor16 ,  R.id.textview_Sensor17 ,  R.id.textview_Sensor18 ,  R.id.textview_Sensor19 ,  R.id.textview_Sensor20 ,
                        R.id.textview_Sensor21 ,  R.id.textview_Sensor22 ,  R.id.textview_Sensor23 ,  R.id.textview_Sensor24 ,  R.id.textview_Sensor25 ,  R.id.textview_Sensor26 ,
                };


                //set number lesson invisibilyty
                for(int i = value; i < numberTotalContent; i++){
                    CardView temp = findViewById(CardView_List[i]);
                    temp.setVisibility(View.GONE);
                }

                // set total number lesson
                for(int i = 0; i < value; i++){
                    TextView tvTemp = findViewById(numberTotalLesson[i]);
                    tvTemp.setText(value+"");
                }

                //set Lesson name
                for(int i = 0; i < value; i++){
                    String lessonTemp = "Lesson"+(i+1);
                    DatabaseReference dataTemp = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lessonTemp).child("Name");
                    textViewTemp = findViewById(nameLesson[i]);


                    Function function = new Function();
                    function.SetDataIntoObject(dataTemp, textViewTemp);
                    Log.d(TAG, "onDataChange: "+textViewTemp.getText().toString());




                }
                for(int i=0;i<value;i++)
                {
                    CardView temp = findViewById(CardView_List[i]);
                    CardView tempColor = findViewById(Cardview_color[i]);
                    boolean hascolor = tempColor.isClickable();
                    ButtonLesson(temp,i+1,textViewTemp.getText().toString(),hascolor);
                    //Log.d(TAG, "onDataChange: "+textViewTemp.getText().toString());
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
    @Override

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Sensors.this, MainActivity.class));
        finish();

    }

//    }

    private void ButtonLesson(CardView button, final int value, final String name, final boolean hascolor)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sensors.this,Tol2_Lesson_Content.class);
                i.putExtra("LESSONNUMBERINTENT",value);
                i.putExtra("HASCOLOR", hascolor);
                Log.d(TAG, "onDataChange: thuyngocha1 "+hascolor);
                i.putExtra("LESSONNAME", name);
                startActivity(i);
            }
        });
    }
}