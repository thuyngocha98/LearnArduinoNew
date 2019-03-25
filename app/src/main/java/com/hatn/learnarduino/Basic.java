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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Tol1.Tol1_Lesson_Content;
import com.hatn.learnarduino.Tol1.Tol1_Lesson_Quiz;
import com.hatn.learnarduino.Tol2.Tol2_Lesson_Content;

public class Basic extends AppCompatActivity {

    TextView tokenTextView;
    private static final String TAG = "Basic_log";
    private CardView btnBasic1,btnBasic2,btnBasic3,btnBasic4,btnBasic5,btnBasic6,btnBasic7,btnBasic8;
    ProgressDialog progressDialog;
    int numberTotalContent = 8;
    public static final String LESSONNUMBERINTENT ="LESSONNUMBERINTENT";
    public static final String HASCOLOR = "HASCOLOR";
    FirebaseAuth mAuth;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //TODO: put ads id here
        MobileAds.initialize(this, getResources().getString(R.string.main_ads_id));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.mInterstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Exp");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                int exp=value.intValue();

//                if (exp==0)
//                {
//                    Intent intent = new Intent(Basic.this,Welcome.class);
//                    intent.putExtra("TypeofSlider", 1);
//                    startActivity(intent);
//                }

                int[] CardViewColor_list = {
                        R.id.tol1_colorcard1 , R.id.tol1_colorcard2, R.id.tol1_colorcard3, R.id.tol1_colorcard4,
                        R.id.tol1_colorcard5 , R.id.tol1_colorcard6, R.id.tol1_colorcard7, R.id.tol1_colorcard8,
                };
                int[] CardView_List = {
                        R.id.btn_Basic1 ,  R.id.btn_Basic2 ,  R.id.btn_Basic3 ,  R.id.btn_Basic4 ,  R.id.btn_Basic5 ,
                        R.id.btn_Basic6 ,  R.id.btn_Basic7 ,  R.id.btn_Basic8 ,
                };

                for(int i =0; i <7; i++){
                    CardView temp = findViewById(CardViewColor_list[i]);
                    CardView Allcard1 = findViewById(CardView_List[i]);
                    CardView Allcard = findViewById(CardView_List[i+1]);
                    if(exp >=5){
                        temp.setCardBackgroundColor(Color.parseColor("#ff669900"));
                        temp.setClickable(false);
                        Allcard1.setAlpha(1);
                        exp -=5;
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



        // set visibility with number of lesson
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Number_of_lesson");
        number1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                //set number lesson visibility
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
                // set card color
                int[] CardViewColor_list = {
                        R.id.tol1_colorcard1 , R.id.tol1_colorcard2, R.id.tol1_colorcard3, R.id.tol1_colorcard4,
                        R.id.tol1_colorcard5 , R.id.tol1_colorcard6, R.id.tol1_colorcard7, R.id.tol1_colorcard8,
                };



                //set number lesson visibility
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
                for (int i=0;i<value;i++)
                {
                    CardView temp = findViewById(CardView_List[i]);
                    CardView tempcolor = findViewById(CardViewColor_list[i]);
                    boolean hascolor = tempcolor.isClickable();
                    ButtonLesson(temp,i+1,hascolor);
                }
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //TODO: enable or disable ads here
        RemoveAd();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
//        Intent intent = new Intent(Basic.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(Basic.this, Gettoken.class);
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

    private void ButtonLesson(CardView button, final int value, final boolean hascolor)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: re-enable ads here
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
//                    Toast.makeText(Basic.this, "Ad did not load", Toast.LENGTH_SHORT).show();
                    Log.d("zzz", "The interstitial wasn't loaded yet.");
                }

                Intent i = new Intent(Basic.this, Tol1_Lesson_Content.class);
                i.putExtra("LESSONNUMBERINTENT",value);
                Log.d(TAG, "test onClick: "+value);
                i.putExtra("HASCOLOR", hascolor);
                startActivity(i);
            }
        });
    }

    private void mergeIdCardView(){
        btnBasic1 = findViewById(R.id.btn_Basic1);
        btnBasic2 = findViewById(R.id.btn_Basic2);
        btnBasic3 = findViewById(R.id.btn_Basic3);
        btnBasic4 = findViewById(R.id.btn_Basic4);
        btnBasic5 = findViewById(R.id.btn_Basic5);
        btnBasic6 = findViewById(R.id.btn_Basic6);
        btnBasic7 = findViewById(R.id.btn_Basic7);
        btnBasic8 = findViewById(R.id.btn_Basic8);
    }

    private void RemoveAd()
    {

        {
            final TextView proversion = new TextView(this);
            proversion.setText("0");
            mAuth = FirebaseAuth.getInstance();

            if (mAuth.getCurrentUser()!=null)
            {
                String user_id1= mAuth.getCurrentUser().getUid();
                final DatabaseReference pro_version_check = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Pro");
                pro_version_check.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("pro", "onDataChange: pro check 1 "+ value);
                        proversion.setText(value+"");

                        int temp = Integer.parseInt(proversion.getText().toString());
                        Log.d("pro", "onDataChange: pro check 2 "+ temp);
                        if (temp==1)
                        {
                            mInterstitialAd = new InterstitialAd(Basic.this);
                        } else {
                            mInterstitialAd = new InterstitialAd(Basic.this);
                            mInterstitialAd.setAdUnitId(getResources().getString(R.string.mInterstitialAd_id));
                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }
        }
    }
}
