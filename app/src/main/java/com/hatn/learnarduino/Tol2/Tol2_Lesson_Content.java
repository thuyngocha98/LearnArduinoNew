package com.hatn.learnarduino.Tol2;

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
import com.hatn.learnarduino.R;
import com.hatn.learnarduino.Sensors;

import java.io.ByteArrayOutputStream;

public class Tol2_Lesson_Content extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    String activity_name, code;
    ImageView imageView1, imageView2;
    FloatingActionButton fab1, fab2, fab;
    boolean isFABOpen;
    Button btnCopy, btnCancel;
    TextView textView, textViewfab1, textViewfab2, codeview, codeviewtemp;
    CardView cardViewfab1, cardViewfab2, cardviewcode;
    ProgressDialog progressDialog;
    public static final String LESSONNUMBERINTENT ="LESSONNUMBERINTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol2_lesson_content);

        final Intent intent = getIntent();

        final int max_basic = intent.getIntExtra("MAXBASIC", 1);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        imageView1=findViewById(R.id.L1C_img1);
        imageView2=findViewById(R.id.L1C_img2);
        textView=findViewById(R.id.L1C_content);
        coordinatorLayout=findViewById(R.id.tol2_content_layout);
        codeviewtemp=findViewById(R.id.textview_codetemp);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);


        DatabaseReference Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Description");
        DatabaseReference Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image1");
        DatabaseReference Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image2");
        DatabaseReference Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Name");
        DatabaseReference Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Code");

        if (intent != null) {
            int Lessonnumber= intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1);

            String Lessonname = intent.getStringExtra(Sensors.LESSONNAME);
            switch (Lessonnumber)
            {
                case 1: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Code");
                    //SetLessonBaseOnNumber(Tol2_lesson1_Content,Tol2_lesson1_img1,Tol2_lesson1_img2,Tol2_lesson1_Name,Tol2_lesson1_Code,"Lesson1");
                    //setTitle(Lessonname);
                    break;
                }
                case 2: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("Code");
                    //SetLessonBaseOnNumber(Tol2_lesson1_Content,Tol2_lesson1_img1,Tol2_lesson1_img2,Tol2_lesson1_Name,Tol2_lesson1_Code,"Lesson2");
                    //setTitle(Lessonname);
                    break;
                }
                case 3: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("Code");
                    //SetLessonBaseOnNumber(Tol2_lesson1_Content,Tol2_lesson1_img1,Tol2_lesson1_img2,Tol2_lesson1_Name,Tol2_lesson1_Code,"Lesson3");
                    //setTitle(Lessonname);
                    break;
                }
                case 4: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("Code");
                    break;
                }
                case 5: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("Code");
                    break;
                }
                case 6: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("Code");
                    break;
                }
                case 7: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("Code");
                    break;
                }
                case 8: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("Code");
                    break;
                }
                case 9: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("Code");
                    break;
                }
                case 10: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("Code");
                    break;
                }
                case 11: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("Code");
                    break;
                }
                case 12: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("Code");
                    break;
                }
                case 13: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("Code");
                    break;
                }
                case 14: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("Code");
                    break;
                }
                case 15: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("Code");
                    break;
                }
                case 16: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("Code");
                    break;
                }
                case 17: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("Code");
                    break;
                }
                case 18: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("Code");
                    break;
                }
                case 19: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("Code");
                    break;
                }
                case 20: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("Code");
                    break;
                }
                case 21: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("Code");
                    break;
                }
                case 22: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("Code");
                    break;
                }
                case 23: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("Code");
                    break;
                }
                case 24: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("Code");
                    break;
                }
                case 25: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("Code");
                    break;
                }
                case 26: {
                    Tol2_lesson_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("Description");
                    Tol2_lesson_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("Image1");
                    Tol2_lesson_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("Image2");
                    Tol2_lesson_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Name");
                    Tol2_lesson_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("Code");
                    break;
                }

            }


        }

        Function function = new Function();
        function.SetDataIntoObject(Tol2_lesson_Content, textView);

        function.SetDataIntoObject(Tol2_lesson_Code,codeviewtemp);

        function.SetDataIntoObject(Tol2_lesson_img1, imageView1);

        Tol2_lesson_Name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                Log.d(TAG, "Label: "+ value);


                getSupportActionBar().setTitle(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Tol2_lesson_img2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value is: " + value);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                imageBytes = Base64.decode(value, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView2.setImageBitmap(decodedImage);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        fab = findViewById(R.id.fab);
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

        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        cardViewfab1=findViewById(R.id.card_view_fab1);
        cardViewfab2=findViewById(R.id.card_view_fab2);


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog=ProgressDialog.show(Tol2_Lesson_Content.this,"Loading app data","Please wait for a while",true);
                showCustomDialog();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkcolor = intent.getBooleanExtra(Sensors.HASCOLOR, true);
                Intent i = new Intent(Tol2_Lesson_Content.this, Tol2_Lesson_Quiz.class);
                i.putExtra("LESSONNUMBERINTENT",intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1));
                i.putExtra("HASCOLOR", checkcolor);
                i.putExtra("MAXBASIC",max_basic);
                Log.d("abcdef", "Sensors: "+max_basic);
                Log.d(TAG, "onDataChange: thuyngocha2 "+checkcolor);
                Log.d(TAG, "Quiz1 onCreate: "+intent.getIntExtra(Sensors.LESSONNUMBERINTENT, 1));
                startActivity(i);
            }
        });







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
//    public void SetLessonBaseOnNumber(DatabaseReference dbContent, DatabaseReference dbimg1, DatabaseReference dbimg2, DatabaseReference dbname, DatabaseReference dbcode, String lesson){
//        dbContent = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lesson).child("Content").child("Description");
//        dbimg1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lesson).child("Content").child("Image1");
//        dbimg2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lesson).child("Content").child("Image2");
//        dbname = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lesson).child("Name");
//        dbcode = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child(lesson).child("Content").child("Code");
//    }
//    public void loadTittle(DatabaseReference databaseReference){
//
//    }
}
