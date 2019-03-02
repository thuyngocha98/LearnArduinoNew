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

public class Basic extends AppCompatActivity {

    private static final String TAG = "LED_log";
    private CardView btnBasic1,btnBasic2,btnBasic3,btnBasic4,btnBasic5,btnBasic6,btnBasic7,btnBasic8;
    ProgressDialog progressDialog;
    int numberTotalContent = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
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
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Number_of_lesson");
        number1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                //set number lesson visibilyty
                int[] CardView_List = {
                        R.id.btn_Basic1 ,  R.id.btn_Basic2 ,  R.id.btn_Basic3 ,  R.id.btn_Basic4 ,  R.id.btn_Basic5 ,
                        R.id.btn_Basic6 ,  R.id.btn_Basic7 ,  R.id.btn_Basic8 ,
                };
                // set total number lesson
                int[] numberTotalLesson = {
                        R.id.totalBasic1_lessonnumber,R.id.totalBasic2_lessonnumber,R.id.totalBasic3_lessonnumber,R.id.totalBasic4_lessonnumber,R.id.totalBasic5_lessonnumber,
                        R.id.totalBasic6_lessonnumber,R.id.totalBasic7_lessonnumber,R.id.totalBasic8_lessonnumber,
                };
                // set name of lesson
                int[] nameLesson = {
                        R.id.textview_Basic1 ,  R.id.textview_Basic2 ,  R.id.textview_Basic3 ,  R.id.textview_Basic4 ,  R.id.textview_Basic5 ,
                        R.id.textview_Basic6 ,  R.id.textview_Basic7 ,  R.id.textview_Basic8 ,
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
                    DatabaseReference dataTemp = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child(lessonTemp).child("Name");
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
        btnBasic1 = (CardView) findViewById(R.id.btn_Basic1);
        btnBasic2 = (CardView) findViewById(R.id.btn_Basic2);
        btnBasic3 = (CardView) findViewById(R.id.btn_Basic3);
        btnBasic4 = (CardView) findViewById(R.id.btn_Basic4);
        btnBasic5 = (CardView) findViewById(R.id.btn_Basic5);
        btnBasic6 = (CardView) findViewById(R.id.btn_Basic6);
        btnBasic7 = (CardView) findViewById(R.id.btn_Basic7);
        btnBasic8 = (CardView) findViewById(R.id.btn_Basic8);
    }
}
