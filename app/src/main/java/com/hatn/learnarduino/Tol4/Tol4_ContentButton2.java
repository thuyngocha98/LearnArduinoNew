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

public class Tol4_ContentButton2 extends AppCompatActivity {

    private LinearLayout linearClick1, linearShow1,linearClick2, linearShow2,linearClick3, linearShow3,linearClick4, linearShow4,linearClick5, linearShow5;
    private ImageView imageView1,imageView2,imageView3;
    private ImageButton buttonUpDown1,buttonUpDown2,buttonUpDown3,buttonUpDown4,buttonUpDown5;
    TextView tokenTextView, tvTitle1,tvTitle2,tvTitle3,tvTitle4,tvTitle5,tvdescription1_1,tvdescription2_1,tvdescription2,tvdescription1_3,tvdescription2_3,tvdescription4,tvdescription5, tvTitleName;
    int flag0 = 0,flag1 = 0,flag2 = 0,flag3 = 0,flag4 = 0;
    Intent intent;
    public static final String LESSONNUMBERINTENT = "LESSONNUMBERINTENT";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol4__content_button2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assignData();

        intent = getIntent();
        final int max_led = intent.getIntExtra("MAXLED", 1);
        progressDialog= ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
        DatabaseReference TitleName = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Name");
        DatabaseReference Title1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Title1");
        DatabaseReference Title2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Title2");
        DatabaseReference Title3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Title3");
        DatabaseReference Title4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Title4");
        DatabaseReference Title5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Title5");
        DatabaseReference Description1_1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Resturant layout").child("Description1");
        DatabaseReference Description2_1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Resturant layout").child("Description2");
        DatabaseReference Description2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Robot design").child("Electronics design").child("Description");
        DatabaseReference Description1_3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Robot design").child("Physical design").child("Description1");
        DatabaseReference Description2_3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Robot design").child("Physical design").child("Description2");
        DatabaseReference Description4 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("kitchen part").child("Description");
        DatabaseReference Description5 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Manager Desk").child("Description");
        DatabaseReference Image1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Resturant layout").child("Image");
        DatabaseReference Image2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Robot design").child("Electronics design").child("Image");
        DatabaseReference Image3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Lesson2").child("Content").child("Robot design").child("Physical design").child("Image");

        Function function = new Function();
        function.SetDataIntoObject(TitleName, tvTitleName);
        function.SetDataIntoObject(Title1,tvTitle1);
        function.SetDataIntoObject(Title2,tvTitle2);
        function.SetDataIntoObject(Title3,tvTitle3);
        function.SetDataIntoObject(Title4,tvTitle4);
        function.SetDataIntoObject(Title5,tvTitle5);
        function.SetDataIntoObject(Description1_1,tvdescription1_1);
        function.SetDataIntoObject(Description2_1,tvdescription2_1);
        function.SetDataIntoObject(Description2,tvdescription2);
        function.SetDataIntoObject(Description1_3,tvdescription1_3);
        function.SetDataIntoObject(Description2_3,tvdescription2_3);
        function.SetDataIntoObject(Description4,tvdescription4);
        function.SetDataIntoObject(Description5,tvdescription5);
        function.SetDataIntoObject(Image1, imageView1);
        function.SetDataIntoObject(Image2, imageView2);
        function.SetDataIntoObject(Image3, imageView3);
        progressDialog.dismiss();

        ExpandButton(buttonUpDown1,linearClick1,linearShow1,1);
        ExpandButton(buttonUpDown2,linearClick2,linearShow2, 2);
        ExpandButton(buttonUpDown3,linearClick3,linearShow3, 3);
        ExpandButton(buttonUpDown4,linearClick4,linearShow4, 4);
        ExpandButton(buttonUpDown5,linearClick5,linearShow5, 5);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_tol1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkcolor = intent.getBooleanExtra(Basic.HASCOLOR, true);
                Intent i = new Intent(Tol4_ContentButton2.this, Tol4_Lesson_Quiz.class);
                i.putExtra(LESSONNUMBERINTENT,intent.getIntExtra(Basic.LESSONNUMBERINTENT, 1));
                i.putExtra("HASCOLOR", checkcolor);
                i.putExtra("MAXLED",max_led);
                startActivity(i);
            }
        });
    }

    private void assignData(){
        tvTitleName = findViewById(R.id.primary_text_btn2_1);
        tvTitle1 = findViewById(R.id.tv_title_btn2_1);
        tvTitle2 = findViewById(R.id.tv_title_btn2_2);
        tvTitle3 = findViewById(R.id.tv_title_btn2_3);
        tvTitle4 = findViewById(R.id.tv_title_btn2_4);
        tvTitle5 = findViewById(R.id.tv_title_btn2_5);
        tvdescription1_1 = findViewById(R.id.tv_decription1_btn2_1);
        tvdescription2_1 = findViewById(R.id.tv_decription2_btn2_1);
        tvdescription2 = findViewById(R.id.tv_decription_btn2_2);
        tvdescription1_3 = findViewById(R.id.tv_decription1_btn2_3);
        tvdescription2_3 = findViewById(R.id.tv_decription2_btn2_3);
        tvdescription4 = findViewById(R.id.tv_decription_btn2_4);
        tvdescription5 = findViewById(R.id.tv_decription_btn2_5);
        linearClick1 = findViewById(R.id.linear_click_btn2_1);
        linearShow1 = findViewById(R.id.linear_show_btn2_1);
        linearClick2 = findViewById(R.id.linear_click_btn2_2);
        linearShow2 = findViewById(R.id.linear_show_btn2_2);
        linearClick3 = findViewById(R.id.linear_click_btn2_3);
        linearShow3 = findViewById(R.id.linear_show_btn2_3);
        linearClick4 = findViewById(R.id.linear_click_btn2_4);
        linearShow4 = findViewById(R.id.linear_show_btn2_4);
        linearClick5 = findViewById(R.id.linear_click_btn2_5);
        linearShow5 = findViewById(R.id.linear_show_btn2_5);
        imageView1 = findViewById(R.id.img_btn2_1);
        imageView2 = findViewById(R.id.img_btn2_2);
        imageView3 = findViewById(R.id.img_btn2_3);
        buttonUpDown1 = findViewById(R.id.expand_button_btn2_1);
        buttonUpDown2 = findViewById(R.id.expand_button_btn2_2);
        buttonUpDown3 = findViewById(R.id.expand_button_btn2_3);
        buttonUpDown4 = findViewById(R.id.expand_button_btn2_4);
        buttonUpDown5 = findViewById(R.id.expand_button_btn2_5);
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
