package com.hatn.learnarduino.Tol4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Basic;
import com.hatn.learnarduino.Function;
import com.hatn.learnarduino.Gettoken;
import com.hatn.learnarduino.R;

public class Tol4_ContentButton3 extends AppCompatActivity {

    private LinearLayout linearClick1, linearShow1,linearClick2, linearShow2,linearClick3, linearShow3,linearClick4, linearShow4,linearClick5, linearShow5,linearClick6, linearShow6,linearClick7, linearShow7,linearClick8, linearShow8,linearClick9, linearShow9;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    private ImageButton buttonUpDown1,buttonUpDown2,buttonUpDown3,buttonUpDown4,buttonUpDown5,buttonUpDown6,buttonUpDown7,buttonUpDown8,buttonUpDown9;
    TextView tokenTextView, tvTitleName, tvTitle1,tvTitle2,tvTitle3, tvTitle4,tvTitle5,tvTitle6, tvTitle7,tvTitle8,tvTitle9,tvdescription1_1,tvdescription2_1,tvdescription2,tvdescription3,tvdescription1_4,tvdescription2_4,
            tvdescription1_5,tvdescription2_5,tvdescription1_6,tvdescription2_6,tvdescription7,tvdescription1_8,tvdescription2_8,tvdescription1_9,tvdescription2_9,tvdescription3_9;
    int flag0 = 0,flag1 = 0,flag2 = 0,flag3 = 0,flag4 = 0,flag5 = 0,flag6 = 0,flag7 = 0,flag8 = 0;
    Intent intent;
    public static final String LESSONNUMBERINTENT = "LESSONNUMBERINTENT";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol4__content_button3);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assignData();
        intent = getIntent();
        final int max_led = intent.getIntExtra("MAXLED", 1);
        progressDialog= ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
        DatabaseReference TitleName = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Name");
        DatabaseReference Title1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title1");
        DatabaseReference Title2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title2");
        DatabaseReference Title3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title3");
        DatabaseReference Title4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title4");
        DatabaseReference Title5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title5");
        DatabaseReference Title6 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title6");
        DatabaseReference Title7 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title7");
        DatabaseReference Title8 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title8");
        DatabaseReference Title9 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Title9");

        DatabaseReference Description1_1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Arduino board").child("Description1");
        DatabaseReference Description2_1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Arduino board").child("Description2");
        DatabaseReference Description2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Power supply").child("Description");
        DatabaseReference Description3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Stepper motor").child("Description");
        DatabaseReference Description1_4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Stepper Driver").child("Description1");
        DatabaseReference Description2_4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Stepper Driver").child("Description2");
        DatabaseReference Description1_5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("IR obstacle sensor").child("Description1");
        DatabaseReference Description2_5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("IR obstacle sensor").child("Description2");
        DatabaseReference Description1_6 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Proximity sensor").child("Description1");
        DatabaseReference Description2_6 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Proximity sensor").child("Description2");
        DatabaseReference Description7 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("LCD display").child("Description");
        DatabaseReference Description1_8 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("BLE 5").child("Description1");
        DatabaseReference Description2_8 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("BLE 5").child("Description2");
        DatabaseReference Description1_9 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Ultrasonic sensor").child("Description1");
        DatabaseReference Description2_9 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Ultrasonic sensor").child("Description2");
        DatabaseReference Description3_9 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Ultrasonic sensor").child("Description3");

        DatabaseReference Image1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Arduino board").child("Image1");
        DatabaseReference Image2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Arduino board").child("Image2");
        DatabaseReference Image3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Stepper Driver").child("Image");
        DatabaseReference Image4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("IR obstacle sensor").child("Image1");
        DatabaseReference Image5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("IR obstacle sensor").child("Image2");
        DatabaseReference Image6 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Proximity sensor").child("Image");
        DatabaseReference Image7 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("LCD display").child("Image");
        DatabaseReference Image8 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("BLE 5").child("Image");
        DatabaseReference Image9 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Ultrasonic sensor").child("Image1");
        DatabaseReference Image10 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson3").child("Content").child("Ultrasonic sensor").child("Image2");

        Function function = new Function();
        function.SetDataIntoObject(TitleName, tvTitleName);
        function.SetDataIntoObject(Title1,tvTitle1);
        function.SetDataIntoObject(Title2,tvTitle2);
        function.SetDataIntoObject(Title3,tvTitle3);
        function.SetDataIntoObject(Title4,tvTitle4);
        function.SetDataIntoObject(Title5,tvTitle5);
        function.SetDataIntoObject(Title6,tvTitle6);
        function.SetDataIntoObject(Title7,tvTitle7);
        function.SetDataIntoObject(Title8,tvTitle8);
        function.SetDataIntoObject(Title9,tvTitle9);

        function.SetDataIntoObject(Description1_1,tvdescription1_1);
        function.SetDataIntoObject(Description2_1,tvdescription2_1);
        function.SetDataIntoObject(Description2,tvdescription2);
        function.SetDataIntoObject(Description3,tvdescription3);
        function.SetDataIntoObject(Description1_4,tvdescription1_4);
        function.SetDataIntoObject(Description2_4,tvdescription2_4);
        function.SetDataIntoObject(Description1_5,tvdescription1_5);
        function.SetDataIntoObject(Description2_5,tvdescription2_5);
        function.SetDataIntoObject(Description1_6,tvdescription1_6);
        function.SetDataIntoObject(Description2_6,tvdescription2_6);
        function.SetDataIntoObject(Description7,tvdescription7);
        function.SetDataIntoObject(Description1_8,tvdescription1_8);
        function.SetDataIntoObject(Description2_8,tvdescription2_8);
        function.SetDataIntoObject(Description1_9,tvdescription1_9);
        function.SetDataIntoObject(Description2_9,tvdescription2_9);
        function.SetDataIntoObject(Description3_9,tvdescription3_9);

        function.SetDataIntoObject(Image1, imageView1);
        function.SetDataIntoObject(Image2, imageView2);
        function.SetDataIntoObject(Image3, imageView3);
        function.SetDataIntoObject(Image4, imageView4);
        function.SetDataIntoObject(Image5, imageView5);
        function.SetDataIntoObject(Image6, imageView6);
        function.SetDataIntoObject(Image7, imageView7);
        function.SetDataIntoObject(Image8, imageView8);
        function.SetDataIntoObject(Image9, imageView9);
        function.SetDataIntoObject(Image10, imageView10);
        progressDialog.dismiss();

        ExpandButton(buttonUpDown1,linearClick1,linearShow1,1);
        ExpandButton(buttonUpDown2,linearClick2,linearShow2, 2);
        ExpandButton(buttonUpDown3,linearClick3,linearShow3, 3);
        ExpandButton(buttonUpDown4,linearClick4,linearShow4, 4);
        ExpandButton(buttonUpDown5,linearClick5,linearShow5, 5);
        ExpandButton(buttonUpDown6,linearClick6,linearShow6, 6);
        ExpandButton(buttonUpDown7,linearClick7,linearShow7, 7);
        ExpandButton(buttonUpDown8,linearClick8,linearShow8, 8);
        ExpandButton(buttonUpDown9,linearClick9,linearShow9, 9);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_tol1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkcolor = intent.getBooleanExtra(Basic.HASCOLOR, true);
                Intent i = new Intent(Tol4_ContentButton3.this, Tol4_Lesson_Quiz.class);
                i.putExtra(LESSONNUMBERINTENT,intent.getIntExtra(Basic.LESSONNUMBERINTENT, 1));
                i.putExtra("HASCOLOR", checkcolor);
                i.putExtra("MAXLED",max_led);
                startActivity(i);
            }
        });
    }

    private void assignData(){

        tvTitleName = findViewById(R.id.primary_text_btn3_1);

        tvTitle1 = findViewById(R.id.tv_title_btn3_1);
        tvTitle2 = findViewById(R.id.tv_title_btn3_2);
        tvTitle3 = findViewById(R.id.tv_title_btn3_3);
        tvTitle4 = findViewById(R.id.tv_title_btn3_4);
        tvTitle5 = findViewById(R.id.tv_title_btn3_5);
        tvTitle6 = findViewById(R.id.tv_title_btn3_6);
        tvTitle7 = findViewById(R.id.tv_title_btn3_7);
        tvTitle8 = findViewById(R.id.tv_title_btn3_8);
        tvTitle9 = findViewById(R.id.tv_title_btn3_9);

        tvdescription1_1 = findViewById(R.id.tv_decription1_btn3_1);
        tvdescription2_1 = findViewById(R.id.tv_decription2_btn3_1);
        tvdescription2 = findViewById(R.id.tv_decription_btn3_2);
        tvdescription3 = findViewById(R.id.tv_decription_btn3_3);
        tvdescription1_4 = findViewById(R.id.tv_decription1_btn3_4);
        tvdescription2_4 = findViewById(R.id.tv_decription2_btn3_4);
        tvdescription1_5 = findViewById(R.id.tv_decription1_btn3_5);
        tvdescription2_5 = findViewById(R.id.tv_decription2_btn3_5);
        tvdescription1_6 = findViewById(R.id.tv_decription1_btn3_6);
        tvdescription2_6 = findViewById(R.id.tv_decription2_btn3_6);
        tvdescription7 = findViewById(R.id.tv_decription_btn3_7);
        tvdescription1_8 = findViewById(R.id.tv_decription1_btn3_8);
        tvdescription2_8 = findViewById(R.id.tv_decription2_btn3_8);
        tvdescription1_9 = findViewById(R.id.tv_decription1_btn3_9);
        tvdescription2_9 = findViewById(R.id.tv_decription2_btn3_9);
        tvdescription3_9 = findViewById(R.id.tv_decription3_btn3_9);

        linearClick1 = findViewById(R.id.linear_click_btn3_1);
        linearShow1 = findViewById(R.id.linear_show_btn3_1);
        linearClick2 = findViewById(R.id.linear_click_btn3_2);
        linearShow2 = findViewById(R.id.linear_show_btn3_2);
        linearClick3 = findViewById(R.id.linear_click_btn3_3);
        linearShow3 = findViewById(R.id.linear_show_btn3_3);
        linearClick4 = findViewById(R.id.linear_click_btn3_4);
        linearShow4 = findViewById(R.id.linear_show_btn3_4);
        linearClick5 = findViewById(R.id.linear_click_btn3_5);
        linearShow5 = findViewById(R.id.linear_show_btn3_5);
        linearClick6 = findViewById(R.id.linear_click_btn3_6);
        linearShow6 = findViewById(R.id.linear_show_btn3_6);
        linearClick7 = findViewById(R.id.linear_click_btn3_7);
        linearShow7 = findViewById(R.id.linear_show_btn3_7);
        linearClick8 = findViewById(R.id.linear_click_btn3_8);
        linearShow8 = findViewById(R.id.linear_show_btn3_8);
        linearClick9 = findViewById(R.id.linear_click_btn3_9);
        linearShow9 = findViewById(R.id.linear_show_btn3_9);

        buttonUpDown1 = findViewById(R.id.expand_button_btn3_1);
        buttonUpDown2 = findViewById(R.id.expand_button_btn3_2);
        buttonUpDown3 = findViewById(R.id.expand_button_btn3_3);
        buttonUpDown4 = findViewById(R.id.expand_button_btn3_4);
        buttonUpDown5 = findViewById(R.id.expand_button_btn3_5);
        buttonUpDown6 = findViewById(R.id.expand_button_btn3_6);
        buttonUpDown7 = findViewById(R.id.expand_button_btn3_7);
        buttonUpDown8 = findViewById(R.id.expand_button_btn3_8);
        buttonUpDown9 = findViewById(R.id.expand_button_btn3_9);

        imageView1 = findViewById(R.id.img1_btn3_1);
        imageView2 = findViewById(R.id.img2_btn3_1);
        imageView3 = findViewById(R.id.img_btn3_4);
        imageView4 = findViewById(R.id.img1_btn3_5);
        imageView5 = findViewById(R.id.img2_btn3_5);
        imageView6 = findViewById(R.id.img_btn3_6);
        imageView7 = findViewById(R.id.img_btn3_7);
        imageView8 = findViewById(R.id.img_btn3_8);
        imageView9 = findViewById(R.id.img1_btn3_9);
        imageView10 = findViewById(R.id.img2_btn3_9);

    }

    void ExpandButton(final ImageButton expand, final LinearLayout linear_click, final LinearLayout linear_show, int t)
    {
        if(t ==1) {
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag0==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag0=1;
                    }
                    else if (flag0==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag0=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==2){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag1==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag1=1;
                    }
                    else if (flag1==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag1=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==3){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag2==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag2=1;
                    }
                    else if (flag2==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag2=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==4){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag3==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag3=1;
                    }
                    else if (flag3==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag3=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==5){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag4==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag4=1;
                    }
                    else if (flag4==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag4=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==6){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag5==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag5=1;
                    }
                    else if (flag5==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag5=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==7){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag6==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag6=1;
                    }
                    else if (flag6==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag6=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==8){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag7==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag7=1;
                    }
                    else if (flag7==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag7=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }
        if(t==9){
            linear_show.setVisibility(View.GONE);
            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag8==0)
                    {
                        linear_show.setVisibility(View.VISIBLE);
                        expand.setImageResource(R.drawable.ic_up);
                        flag8=1;
                    }
                    else if (flag8==1)
                    {
                        linear_show.setVisibility(View.GONE);
                        expand.setImageResource(R.drawable.ic_down);
                        flag8=0;
                    }

                }
            });

            linear_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expand.callOnClick();
                }
            });
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(Movement.this, MainActivity.class));
        finish();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(this, Gettoken.class);
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
}
