package com.hatn.learnarduino.Tol2.Lesson1;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import java.io.ByteArrayOutputStream;

public class Lesson1Quiz extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    TextView textviewquiz1, textviewquiz2, textviewquiz3, textviewquiz1_answer1, textviewquiz1_answer2, textviewquiz1_answer3, textviewquiz2_answer1, textviewquiz2_answer2, textviewquiz2_answer3, textviewquiz3_answer1, textviewquiz3_answer2, textviewquiz3_answer3;
    TextView quiz1_rightanswer, quiz2_rightanswer, quiz3_rightanswer;
    CardView cardviewquiz1_answer1, cardviewquiz1_answer2, cardviewquiz1_answer3, cardviewquiz2_answer1, cardviewquiz2_answer2, cardviewquiz2_answer3, cardviewquiz3_answer1, cardviewquiz3_answer2, cardviewquiz3_answer3;
    CardView cardview_quiz1_answer1_flag, cardview_quiz1_answer2_flag, cardview_quiz1_answer3_flag, cardview_quiz2_answer1_flag, cardview_quiz2_answer2_flag, cardview_quiz2_answer3_flag, cardview_quiz3_answer1_flag, cardview_quiz3_answer2_flag, cardview_quiz3_answer3_flag;
    Function function;
    ProgressDialog progressDialog;
    int counttemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1_quiz);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);

        coordinatorLayout=findViewById(R.id.Lesson1Quizlayout);

        textviewquiz1 = findViewById(R.id.textView_Lesson1_Quiz1);
        textviewquiz2 = findViewById(R.id.textView_Lesson1_Quiz2);
        textviewquiz3 = findViewById(R.id.textView_Lesson1_Quiz3);

        textviewquiz1_answer1 = findViewById(R.id.textview_lesson1_quiz1_answer1);
        textviewquiz1_answer2 = findViewById(R.id.textview_lesson1_quiz1_answer2);
        textviewquiz1_answer3 = findViewById(R.id.textview_lesson1_quiz1_answer3);

        textviewquiz2_answer1 = findViewById(R.id.textview_lesson1_quiz2_answer1);
        textviewquiz2_answer2 = findViewById(R.id.textview_lesson1_quiz2_answer2);
        textviewquiz2_answer3 = findViewById(R.id.textview_lesson1_quiz2_answer3);

        textviewquiz3_answer1 = findViewById(R.id.textview_lesson1_quiz3_answer1);
        textviewquiz3_answer2 = findViewById(R.id.textview_lesson1_quiz3_answer2);
        textviewquiz3_answer3 = findViewById(R.id.textview_lesson1_quiz3_answer3);

        cardviewquiz1_answer1 = findViewById(R.id.cardview_quiz1_answer1);
        cardviewquiz1_answer2 = findViewById(R.id.cardview_quiz1_answer2);
        cardviewquiz1_answer3 = findViewById(R.id.cardview_quiz1_answer3);

        cardviewquiz2_answer1 = findViewById(R.id.cardview_quiz1_answer1);
        cardviewquiz2_answer2 = findViewById(R.id.cardview_quiz2_answer2);
        cardviewquiz2_answer3 = findViewById(R.id.cardview_quiz2_answer3);

        cardviewquiz3_answer1 = findViewById(R.id.cardview_quiz3_answer1);
        cardviewquiz3_answer2 = findViewById(R.id.cardview_quiz3_answer2);
        cardviewquiz3_answer3 = findViewById(R.id.cardview_quiz3_answer3);

        cardview_quiz1_answer1_flag = findViewById(R.id.cardview_quiz1_answer1_flag);
        cardview_quiz1_answer2_flag = findViewById(R.id.cardview_quiz1_answer2_flag);
        cardview_quiz1_answer3_flag = findViewById(R.id.cardview_quiz1_answer3_flag);

        cardview_quiz2_answer1_flag = findViewById(R.id.cardview_quiz2_answer1_flag);
        cardview_quiz2_answer2_flag = findViewById(R.id.cardview_quiz2_answer2_flag);
        cardview_quiz2_answer3_flag = findViewById(R.id.cardview_quiz2_answer3_flag);

        cardview_quiz3_answer1_flag = findViewById(R.id.cardview_quiz3_answer1_flag);
        cardview_quiz3_answer2_flag = findViewById(R.id.cardview_quiz3_answer2_flag);
        cardview_quiz3_answer3_flag = findViewById(R.id.cardview_quiz3_answer3_flag);

        quiz1_rightanswer=findViewById(R.id.quiz1_rightanswer);
        quiz2_rightanswer=findViewById(R.id.quiz2_rightanswer);
        quiz3_rightanswer=findViewById(R.id.quiz3_rightanswer);




        DatabaseReference Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
        DatabaseReference Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
        DatabaseReference Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

        DatabaseReference Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
        DatabaseReference Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
        DatabaseReference Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
        DatabaseReference Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

        DatabaseReference Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
        DatabaseReference Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
        DatabaseReference Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
        DatabaseReference Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

        DatabaseReference Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
        DatabaseReference Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
        DatabaseReference Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
        DatabaseReference Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");


        function=new Function();

        function.SetDataIntoObject(Getquiz1question,textviewquiz1);
        function.SetDataIntoObject(Getquiz2question,textviewquiz2);
        function.SetDataIntoObject(Getquiz3question,textviewquiz3);

        function.SetDataIntoObject(Getquiz1rightanswer,quiz1_rightanswer);
        function.SetDataIntoObject(Getquiz2rightanswer,quiz2_rightanswer);
        function.SetDataIntoObject(Getquiz3rightanswer,quiz3_rightanswer);

        function.SetDataIntoObject(Getquiz1answer1,textviewquiz1_answer1);
        function.SetDataIntoObject(Getquiz1answer2,textviewquiz1_answer2);
        function.SetDataIntoObject(Getquiz1answer3,textviewquiz1_answer3);

        function.SetDataIntoObject(Getquiz2answer1,textviewquiz2_answer1);
        function.SetDataIntoObject(Getquiz2answer2,textviewquiz2_answer2);
        function.SetDataIntoObject(Getquiz2answer3,textviewquiz2_answer3);

        function.SetDataIntoObject(Getquiz3answer1,textviewquiz3_answer1);
        function.SetDataIntoObject(Getquiz3answer2,textviewquiz3_answer2);
        function.SetDataIntoObject(Getquiz3answer3,textviewquiz3_answer3);

        progressDialog.dismiss();

        cardviewquiz1_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("check true", "onClick: "+quiz1_rightanswer.getText().toString());
                Log.d("check true", "onClick: "+textviewquiz1_answer1.getText().toString());

                if (textviewquiz1_answer1.getText().toString().equals(quiz1_rightanswer.getText().toString()))
                {
                    RightAnswer(cardview_quiz1_answer1_flag);
                    cardviewquiz1_answer1.setClickable(false);
                    cardviewquiz1_answer2.setClickable(false);
                    cardviewquiz1_answer3.setClickable(false);
                }
                else WrongAnswer(cardview_quiz1_answer1_flag);
            }
        });

        cardviewquiz1_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textviewquiz1_answer2.getText().equals(quiz1_rightanswer.getText()))
                {
                    RightAnswer(cardview_quiz1_answer2_flag);
                    cardviewquiz1_answer1.setClickable(false);
                    cardviewquiz1_answer2.setClickable(false);
                    cardviewquiz1_answer3.setClickable(false);
                }
                else WrongAnswer(cardview_quiz1_answer2_flag);
            }
        });

        cardviewquiz1_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textviewquiz1_answer3.getText().equals(quiz1_rightanswer.getText()))
                {
                    RightAnswer(cardview_quiz1_answer3_flag);
                    cardviewquiz1_answer1.setClickable(false);
                    cardviewquiz1_answer2.setClickable(false);
                    cardviewquiz1_answer3.setClickable(false);
                }
                else WrongAnswer(cardview_quiz1_answer3_flag);
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counttemp!=3)
                {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please answer all questions before moving to next lesson ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    //TODO: intent to next activity
                }
            }
        });


//        fab1 = findViewById(R.id.fab1);
//        fab2 = findViewById(R.id.fab2);
//        cardViewfab1=findViewById(R.id.card_view_fab1);
//        cardViewfab2=findViewById(R.id.card_view_fab2);



//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Lesson1Quiz.this, Lesson1Content.class);
//                startActivity(i);
//            }
//        });







    }

    private void RightAnswer(CardView cardView){
        //cardView.setCardBackgroundColor(getResources().getColor(R.color.Primary));
        cardView.setVisibility(View.VISIBLE);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Congrats! Right answer ", Snackbar.LENGTH_LONG);
        snackbar.show();
        cardView.setVisibility(View.INVISIBLE);

    }
    private void WrongAnswer(CardView cardView){
        //cardView.setCardBackgroundColor(getResources().getColor(R.color.Primary));
        cardView.setVisibility(View.VISIBLE);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Please try again ", Snackbar.LENGTH_LONG);
        snackbar.show();
        cardView.setVisibility(View.INVISIBLE);

    }

//    private void showCustomDialog() {
//        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        //then we will inflate the custom alert dialog xml that we created
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_viewcode, viewGroup, false);
//
//        codeview = dialogView.findViewById(R.id.textview_code);
//        codeview.setText(code);
//        codeview.setMovementMethod(new ScrollingMovementMethod());
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        final AlertDialog alertDialog = builder.create();
//
//        btnCopy = dialogView.findViewById(R.id.buttonCopy);
//        btnCancel = dialogView.findViewById(R.id.buttonCancel);
//
//        btnCopy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("Copied Code", code);
//                clipboard.setPrimaryClip(clip);
//
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Copied successfully ", Snackbar.LENGTH_LONG);
//                snackbar.show();
//
//                closeFABMenu();
//                alertDialog.dismiss();
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Cancelled ", Snackbar.LENGTH_LONG);
//                snackbar.show();
//
//                closeFABMenu();
//                alertDialog.dismiss();
//            }
//        });
//
//
//        alertDialog.show();
//    }
//
//    public void showFABMenu(){
//        isFABOpen=true;
//        fab.setImageResource(R.drawable.cancel);
//        cardViewfab1.setVisibility(View.VISIBLE);
//        cardViewfab2.setVisibility(View.VISIBLE);
//        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        cardViewfab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//        cardViewfab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//
//
//
//    }
//
//    public void closeFABMenu(){
//        isFABOpen=false;
//        fab.setImageResource(R.drawable.ic_menu);
//        cardViewfab1.animate().translationY(0);
//        cardViewfab2.animate().translationY(0);
//        cardViewfab1.setVisibility(View.INVISIBLE);
//        cardViewfab2.setVisibility(View.INVISIBLE);
//        fab1.animate().translationY(0);
//        fab2.animate().translationY(0);
//
//    }

}
