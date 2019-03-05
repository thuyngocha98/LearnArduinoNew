package com.hatn.learnarduino.Tol3;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Function;
import com.hatn.learnarduino.LED;
import com.hatn.learnarduino.R;
import com.hatn.learnarduino.Sensors;

import java.io.ByteArrayOutputStream;

public class Tol3_Lesson_Content extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    String code;
    ImageView imageView;
    FloatingActionButton fab1, fab2, fab;
    boolean isFABOpen;
    Button btnCopy, btnCancel;
    TextView textView1, textView2, codeview, codeviewtemp;
    CardView cardViewfab1, cardViewfab2;
    ProgressDialog progressDialog;
    public static final String LESSONNUMBERINTENT ="LESSONNUMBERINTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol3_lesson_content);

        final Intent intent = getIntent();
        final int max_sensor = intent.getIntExtra("MAXSENSOR", 1);


        Toolbar toolbar = findViewById(R.id.toolbar_tol3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        imageView=findViewById(R.id.L1C_img_tol3);
        textView1=findViewById(R.id.L1C_content1_tol3);
        textView2=findViewById(R.id.L1C_content2_tol3);
        coordinatorLayout=findViewById(R.id.tol3_lesson_content_layout);
        codeviewtemp=findViewById(R.id.textview_codetemp_tol3);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);


        DatabaseReference Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Description1");
        DatabaseReference Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Description1");
        DatabaseReference Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Image");
        DatabaseReference Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Name");
        DatabaseReference Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Code");

        if (intent != null) {
            int Lessonnumber= intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1);

            //String Lessonname = intent.getStringExtra(Sensors.LESSONNAME);
            switch (Lessonnumber)
            {
                case 1: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("Code");
                    break;
                }
                case 2: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("Code");
                    break;
                }
                case 3: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("Code");
                    break;
                }
                case 4: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("Code");
                    break;
                }
                case 5: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("Code");
                    break;
                }
                case 6: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("Code");
                    break;
                }
                case 7: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("Code");
                    break;
                }
                case 8: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("Code");
                    break;
                }
                case 9: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("Code");
                    break;
                }
                case 10: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("Code");
                    break;
                }
                case 11: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("Code");
                    break;
                }
                case 12: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("Code");
                    break;
                }
                case 13: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("Code");
                    break;
                }
                case 14: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("Code");
                    break;
                }
                case 15: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("Code");
                    break;
                }
                case 16: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("Code");
                    break;
                }
                case 17: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("Code");
                    break;
                }
                case 18: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("Description1");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("Code");
                    break;
                }
                case 19: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("Code");
                    break;
                }
                case 20: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("Code");
                    break;
                }
                case 21: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("Code");
                    break;
                }
                case 22: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("Code");
                    break;
                }
                case 23: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("Code");
                    break;
                }
                case 24: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("Code");
                    break;
                }
                case 25: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("Code");
                    break;
                }
                case 26: {
                    Tol3_lesson_Content1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Description1");
                    Tol3_lesson_Content2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Description2");
                    Tol3_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Image");
                    Tol3_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Name");
                    Tol3_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("Code");
                    break;
                }

            }


        }

        Function function = new Function();
        function.SetDataIntoObject(Tol3_lesson_Content1, textView1);
        function.SetDataIntoObject(Tol3_lesson_Content2,textView2);
        function.SetDataIntoObject(Tol3_lesson_Code,codeviewtemp);
        function.SetDataIntoObject(Tol3_lesson_img1, imageView);
        progressDialog.dismiss();

//        Tol3_lesson_Name.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue().toString();
//                Log.d(TAG, "Label: "+ value);
//                getSupportActionBar().setTitle(value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        fab = findViewById(R.id.fab_tol3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab1 = findViewById(R.id.fab1_tol3);
        fab2 = findViewById(R.id.fab2_tol3);
        cardViewfab1=findViewById(R.id.card_view_fab1_tol3);
        cardViewfab2=findViewById(R.id.card_view_fab2_tol3);


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog=ProgressDialog.show(Tol3_Lesson_Content.this,"Loading app data","Please wait for a while",true);
                showCustomDialog();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkcolor = intent.getBooleanExtra(Sensors.HASCOLOR, true);
                Intent i = new Intent(Tol3_Lesson_Content.this, Tol3_Lesson_Quiz.class);
                i.putExtra("LESSONNUMBERINTENT",intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1));
                i.putExtra("HASCOLOR", checkcolor);
                i.putExtra("MAXSENSOR",max_sensor);
                Log.d(TAG, "Quiz1 onCreate: "+intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1));
                startActivity(i);
            }
        });








    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Tol3_Lesson_Content.this, LED.class));
        finish();

    }


    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_viewcode, viewGroup, false);

        codeview = dialogView.findViewById(R.id.textview_code);
        codeview.setText(codeviewtemp.getText());
        codeview.setMovementMethod(new ScrollingMovementMethod());

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        btnCopy = dialogView.findViewById(R.id.buttonCopy);
        btnCancel = dialogView.findViewById(R.id.buttonCancel);

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Code", code);
                clipboard.setPrimaryClip(clip);

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Copied successfully ", 50);
                snackbar.show();

                closeFABMenu();
                alertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Cancelled ", 50);
                snackbar.show();

                closeFABMenu();
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
        progressDialog.dismiss();
    }

    public void showFABMenu(){
        isFABOpen=true;
        fab.setImageResource(R.drawable.cancel);
        cardViewfab1.setVisibility(View.VISIBLE);
        cardViewfab2.setVisibility(View.VISIBLE);
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        cardViewfab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        cardViewfab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));

    }

    public void closeFABMenu(){
        isFABOpen=false;
        fab.setImageResource(R.drawable.ic_menu);
        cardViewfab1.animate().translationY(0);
        cardViewfab2.animate().translationY(0);
        cardViewfab1.setVisibility(View.INVISIBLE);
        cardViewfab2.setVisibility(View.INVISIBLE);
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);

    }
}
