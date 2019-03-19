package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Tol3.Tol3_Lesson_Content;

public class LED extends AppCompatActivity {

    TextView tokenTextView;
    private static final String TAG = "LED_log";
    ProgressDialog progressDialog;
    int numberTotalContent = 26;
    FirebaseAuth mAuth;
    Intent intent;
    int max_sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        max_sensor = intent.getIntExtra("MAXSENSOR2", 0);
        if(max_sensor == 0)
            max_sensor = intent.getIntExtra("MAXSENSOR", 0);


        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Exp");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                int exp=value.intValue();
                int maxsensor = intent.getIntExtra("MAXSENSOR2", 0);
                if(maxsensor == 0)
                    maxsensor = intent.getIntExtra("MAXSENSOR",1);
                int expLed = exp - maxsensor;
                Log.d(TAG, "onDataChange: ha"+ expLed+ " max "+maxsensor+" exp"+exp);

                int[] Cardview_color = {
                        R.id.cardview_color_led1,R.id.cardview_color_led2,R.id.cardview_color_led3,R.id.cardview_color_led4,R.id.cardview_color_led5,
                        R.id.cardview_color_led6,R.id.cardview_color_led7,R.id.cardview_color_led8,R.id.cardview_color_led9,R.id.cardview_color_led10,
                        R.id.cardview_color_led11,R.id.cardview_color_led12,R.id.cardview_color_led13,R.id.cardview_color_led14,R.id.cardview_color_led15,
                        R.id.cardview_color_led16,R.id.cardview_color_led17,R.id.cardview_color_led18,R.id.cardview_color_led19,R.id.cardview_color_led20,
                        R.id.cardview_color_led21,R.id.cardview_color_led22,R.id.cardview_color_led23,R.id.cardview_color_led24,R.id.cardview_color_led25,
                        R.id.cardview_color_led26,
                };
                int[] CardView_List = {
                        R.id.btn_Led1 ,  R.id.btn_Led2 ,  R.id.btn_Led3 ,  R.id.btn_Led4 ,  R.id.btn_Led5 ,
                        R.id.btn_Led6 ,  R.id.btn_Led7 ,  R.id.btn_Led8 ,  R.id.btn_Led9 ,  R.id.btn_Led10 ,
                        R.id.btn_Led11 ,  R.id.btn_Led12 ,  R.id.btn_Led13 ,  R.id.btn_Led14 ,  R.id.btn_Led15 ,
                        R.id.btn_Led16 ,  R.id.btn_Led17 ,  R.id.btn_Led18 ,  R.id.btn_Led19 ,  R.id.btn_Led20 ,
                        R.id.btn_Led21 ,  R.id.btn_Led22 ,  R.id.btn_Led23 ,  R.id.btn_Led24 ,  R.id.btn_Led25 ,  R.id.btn_Led26 ,
                };

                for(int i =0; i <25; i++){
                    CardView temp = findViewById(Cardview_color[i]);
                    CardView Allcard1 = findViewById(CardView_List[i]);
                    CardView Allcard = findViewById(CardView_List[i+1]);
                    if(expLed >=5){
                        temp.setCardBackgroundColor(Color.parseColor("#ff669900"));
                        temp.setClickable(false);
                        Allcard1.setAlpha(1);
                        expLed -=5;
                    }
                    else{
                        Allcard.setEnabled(false);
                        Allcard.setAlpha(.5f);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

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
                //cardView color
                int[] Cardview_color = {
                        R.id.cardview_color_led1,R.id.cardview_color_led2,R.id.cardview_color_led3,R.id.cardview_color_led4,R.id.cardview_color_led5,
                        R.id.cardview_color_led6,R.id.cardview_color_led7,R.id.cardview_color_led8,R.id.cardview_color_led9,R.id.cardview_color_led10,
                        R.id.cardview_color_led11,R.id.cardview_color_led12,R.id.cardview_color_led13,R.id.cardview_color_led14,R.id.cardview_color_led15,
                        R.id.cardview_color_led16,R.id.cardview_color_led17,R.id.cardview_color_led18,R.id.cardview_color_led19,R.id.cardview_color_led20,
                        R.id.cardview_color_led21,R.id.cardview_color_led22,R.id.cardview_color_led23,R.id.cardview_color_led24,R.id.cardview_color_led25,
                        R.id.cardview_color_led26,
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

                    CardView temp = findViewById(CardView_List[i]);
                    CardView tempColor = findViewById(Cardview_color[i]);
                    boolean hascolor = tempColor.isClickable();
                    ButtonLesson(temp,i+1,textViewTemp.getText().toString(),hascolor);

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
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(LED.this, Gettoken.class);
                startActivity(intent);
                return true;

            default:
                onBackPressed();
                return true;
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.activity_main_menu);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        LinearLayout rootView = (LinearLayout) alertMenuItem.getActionView();

        tokenTextView = (TextView) rootView.findViewById(R.id.menu_item_number);
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                tokenTextView.setText(value.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(LED.this, MainActivity.class));
        finish();

    }

    private void ButtonLesson(CardView button, final int value, final String name,final boolean hascolor)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LED.this,Tol3_Lesson_Content.class);
                i.putExtra("LESSONNUMBERINTENT",value);
                i.putExtra("HASCOLOR", hascolor);
                i.putExtra("MAXSENSOR", max_sensor);
                i.putExtra("LESSONNAME", name);
                startActivity(i);
            }
        });
    }
}
