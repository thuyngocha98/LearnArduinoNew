package com.hatn.learnarduino.Tol1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hatn.learnarduino.Basic;
import com.hatn.learnarduino.Function;
import com.hatn.learnarduino.LED;
import com.hatn.learnarduino.R;
import com.hatn.learnarduino.Sensors;
import com.hatn.learnarduino.Tol1.Tol1_Lesson_Content;
import com.hatn.learnarduino.Tol1.Tol1_Lesson_Quiz;

public class Tol1_Lesson_Content extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    CoordinatorLayout coordinatorLayout;
    ProgressDialog progressDialog;
    Intent intent;
    public static final String LESSONNUMBERINTENT = "LESSONNUMBERINTENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to1_lesson_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tol1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        imageView=findViewById(R.id.Tol1_img);
        textView=findViewById(R.id.Tol1_content);
        coordinatorLayout=findViewById(R.id.tol1_layout);

        intent = getIntent();
        progressDialog= ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
        DatabaseReference Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson26").child("Content").child("Description");
        DatabaseReference Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson1").child("Content").child("Image");

        if (intent != null) {
            int Lessonnumber= intent.getIntExtra(Basic.LESSONNUMBERINTENT, 1);
            Log.d("Basic", "test onClick: "+ Lessonnumber);

            //String Lessonname = intent.getStringExtra(Sensors.LESSONNAME);
            switch (Lessonnumber)
            {
                case 1: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson1").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson1").child("Content").child("Image");
                    break;
                }
                case 2: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson2").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson2").child("Content").child("Image");
                    break;
                }
                case 3: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson3").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson3").child("Content").child("Image");
                    break;
                }
                case 4: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson4").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson4").child("Content").child("Image");
                    break;
                }
                case 5: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson5").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson5").child("Content").child("Image");
                    break;
                }
                case 6: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson6").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson6").child("Content").child("Image");
                    break;
                }
                case 7: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson7").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson7").child("Content").child("Image");
                    break;
                }
                case 8: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson8").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson8").child("Content").child("Image");
                    break;
                }
                case 9: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson9").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson9").child("Content").child("Image");
                    break;
                }
                case 10: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson10").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson10").child("Content").child("Image");
                    break;
                }
                case 11: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson11").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson11").child("Content").child("Image");
                    break;
                }
                case 12: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson12").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson12").child("Content").child("Image");
                    break;
                }
                case 13: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson13").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson13").child("Content").child("Image");
                    break;
                }
                case 14: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson14").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson14").child("Content").child("Image");
                    break;
                }
                case 15: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson15").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson15").child("Content").child("Image");
                    break;
                }
                case 16: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson16").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson16").child("Content").child("Image");
                    break;
                }
                case 17: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson17").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson17").child("Content").child("Image");
                    break;
                }
                case 18: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson18").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson18").child("Content").child("Image");
                    break;
                }
                case 19: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson19").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson19").child("Content").child("Image");
                    break;
                }
                case 20: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson20").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson20").child("Content").child("Image");
                    break;
                }
                case 21: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson21").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson21").child("Content").child("Image");
                    break;
                }
                case 22: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson22").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson22").child("Content").child("Image");
                    break;
                }
                case 23: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson23").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson23").child("Content").child("Image");
                    break;
                }
                case 24: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson24").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson24").child("Content").child("Image");
                    break;
                }
                case 25: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson25").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson25").child("Content").child("Image");
                    break;
                }
                case 26: {
                    Tol1_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson26").child("Content").child("Description");
                    Tol1_lesson_img = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Lesson26").child("Content").child("Image");
                    break;
                }

            }


        }

        Function function = new Function();
        function.SetDataIntoObject(Tol1_lesson_Content, textView);
        function.SetDataIntoObject(Tol1_lesson_img, imageView);
        progressDialog.dismiss();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_tol1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkcolor = intent.getBooleanExtra(Basic.HASCOLOR, true);
                Intent i = new Intent(Tol1_Lesson_Content.this,Tol1_Lesson_Quiz.class);
                i.putExtra(LESSONNUMBERINTENT,intent.getIntExtra(Basic.LESSONNUMBERINTENT, 1));
                i.putExtra("HASCOLOR", checkcolor);
                startActivity(i);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(Tol1_Lesson_Content.this, Basic.class));
        finish();
    }
}
