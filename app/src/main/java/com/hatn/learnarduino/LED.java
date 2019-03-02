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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Tol2.Lesson1.Lesson1Content;

import java.util.HashMap;
import java.util.Map;

public class LED extends AppCompatActivity {

    private static final String TAG = "LED_log";
    private CardView btnLED1,btnLED2,btnLED3,btnLED4,btnLED5,btnLED6,btnLED7,btnLED8,btnLED9, btnLED10,btnLED11,btnLED12,
            btnLED13,btnLED14,btnLED15,btnLED16,btnLED17,btnLED18,btnLED19,btnLED20,btnLED21,btnLED22,btnLED23,btnLED24,btnLED25,btnLED26;
    ProgressDialog progressDialog;
    int numberTotalContent = 26;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mergeIdCardView();

//        btnSenser1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LED.this, Lesson1Content.class);
//                startActivity(i);
//            }
//        });

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        // set visibility with number of lesson
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Number_of_lesson");
        number1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                //set number lesson visibilyty
                int[] CardView_List = {
                        R.id.btn_Led1 ,  R.id.btn_Led2 ,  R.id.btn_Led3 ,  R.id.btn_Led4 ,  R.id.btn_Led5 ,
                        R.id.btn_Led6 ,  R.id.btn_Led7 ,  R.id.btn_Led8 ,  R.id.btn_Led9 ,  R.id.btn_Led10 ,
                        R.id.btn_Led11 ,  R.id.btn_Led12 ,  R.id.btn_Led13 ,  R.id.btn_Led14 ,  R.id.btn_Led15 ,
                        R.id.btn_Led16 ,  R.id.btn_Led17 ,  R.id.btn_Led18 ,  R.id.btn_Led19 ,  R.id.btn_Led20 ,
                        R.id.btn_Led21 ,  R.id.btn_Led22 ,  R.id.btn_Led23 ,  R.id.btn_Led24 ,  R.id.btn_Led25 ,  R.id.btn_Led26 ,
                };
                // set total number lesson
                int[] numberTotalLesson = {
                        R.id.totalLed1_lessonnumber,R.id.totalLed2_lessonnumber,R.id.totalLed3_lessonnumber,R.id.totalLed4_lessonnumber,R.id.totalLed5_lessonnumber,
                        R.id.totalLed6_lessonnumber,R.id.totalLed7_lessonnumber,R.id.totalLed8_lessonnumber,R.id.totalLed9_lessonnumber,R.id.totalLed10_lessonnumber,
                        R.id.totalLed11_lessonnumber,R.id.totalLed12_lessonnumber,R.id.totalLed13_lessonnumber,R.id.totalLed14_lessonnumber,R.id.totalLed15_lessonnumber,
                        R.id.totalLed16_lessonnumber,R.id.totalLed17_lessonnumber,R.id.totalLed18_lessonnumber,R.id.totalLed19_lessonnumber,R.id.totalLed20_lessonnumber,
                        R.id.totalLed21_lessonnumber,R.id.totalLed22_lessonnumber,R.id.totalLed23_lessonnumber,R.id.totalLed24_lessonnumber,R.id.totalLed25_lessonnumber,R.id.totalLed26_lessonnumber,

                };
                // set name of lesson
                int[] nameLesson = {
                        R.id.textview_Led1 ,  R.id.textview_Led2 ,  R.id.textview_Led3 ,  R.id.textview_Led4 ,  R.id.textview_Led5 ,
                        R.id.textview_Led6 ,  R.id.textview_Led7 ,  R.id.textview_Led8 ,  R.id.textview_Led9 ,  R.id.textview_Led10 ,
                        R.id.textview_Led11 ,  R.id.textview_Led12 ,  R.id.textview_Led13 ,  R.id.textview_Led14 ,  R.id.textview_Led15 ,
                        R.id.textview_Led16 ,  R.id.textview_Led17 ,  R.id.textview_Led18 ,  R.id.textview_Led19 ,  R.id.textview_Led20 ,
                        R.id.textview_Led21 ,  R.id.textview_Led22 ,  R.id.textview_Led23 ,  R.id.textview_Led24 ,  R.id.textview_Led25 ,  R.id.textview_Led26 ,
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
                    DatabaseReference dataTemp = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child(lessonTemp).child("Name");
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
        btnLED1 = (CardView) findViewById(R.id.btn_Led1);
        btnLED2 = (CardView) findViewById(R.id.btn_Led2);
        btnLED3 = (CardView) findViewById(R.id.btn_Led3);
        btnLED4 = (CardView) findViewById(R.id.btn_Led4);
        btnLED5 = (CardView) findViewById(R.id.btn_Led5);
        btnLED6 = (CardView) findViewById(R.id.btn_Led6);
        btnLED7 = (CardView) findViewById(R.id.btn_Led7);
        btnLED8 = (CardView) findViewById(R.id.btn_Led8);
        btnLED9 = (CardView) findViewById(R.id.btn_Led9);
        btnLED10 = (CardView) findViewById(R.id.btn_Led10);
        btnLED11 = (CardView) findViewById(R.id.btn_Led11);
        btnLED12 = (CardView) findViewById(R.id.btn_Led12);
        btnLED13 = (CardView) findViewById(R.id.btn_Led13);
        btnLED14 = (CardView) findViewById(R.id.btn_Led14);
        btnLED15 = (CardView) findViewById(R.id.btn_Led15);
        btnLED16 = (CardView) findViewById(R.id.btn_Led16);
        btnLED17 = (CardView) findViewById(R.id.btn_Led17);
        btnLED18 = (CardView) findViewById(R.id.btn_Led18);
        btnLED19 = (CardView) findViewById(R.id.btn_Led19);
        btnLED20 = (CardView) findViewById(R.id.btn_Led20);
        btnLED21 = (CardView) findViewById(R.id.btn_Led21);
        btnLED21 = (CardView) findViewById(R.id.btn_Led22);
        btnLED23 = (CardView) findViewById(R.id.btn_Led23);
        btnLED24 = (CardView) findViewById(R.id.btn_Led24);
        btnLED25 = (CardView) findViewById(R.id.btn_Led25);
        btnLED26 = (CardView) findViewById(R.id.btn_Led26);
    }
}
