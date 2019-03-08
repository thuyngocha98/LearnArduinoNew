package com.hatn.learnarduino.Tol2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Function;
import com.hatn.learnarduino.R;
import com.hatn.learnarduino.Sensors;

public class Tol2_Lesson_Quiz extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    TextView textviewquiz1, textviewquiz2, textviewquiz3, textviewquiz1_answer1, textviewquiz1_answer2, textviewquiz1_answer3, textviewquiz2_answer1, textviewquiz2_answer2, textviewquiz2_answer3, textviewquiz3_answer1, textviewquiz3_answer2, textviewquiz3_answer3;
    TextView quiz1_rightanswer, quiz2_rightanswer, quiz3_rightanswer;
    CardView cardviewquiz1_answer1, cardviewquiz1_answer2, cardviewquiz1_answer3, cardviewquiz2_answer1, cardviewquiz2_answer2, cardviewquiz2_answer3, cardviewquiz3_answer1, cardviewquiz3_answer2, cardviewquiz3_answer3;
    CardView cardview_quiz1_answer1_flag, cardview_quiz1_answer2_flag, cardview_quiz1_answer3_flag, cardview_quiz2_answer1_flag, cardview_quiz2_answer2_flag, cardview_quiz2_answer3_flag, cardview_quiz3_answer1_flag, cardview_quiz3_answer2_flag, cardview_quiz3_answer3_flag;
    Function function;
    ProgressDialog progressDialog;
    Vibrator vibrator;

    public static final String Coloredcard = "Coloredcard";
    private FirebaseAuth mAuth;
    int Lessonnumber;
    Intent intent;

    public static final String LESSONNUMBERINTENT="LESSONNUMBERINTENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol2_lesson_quiz);

        intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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

        cardviewquiz2_answer1 = findViewById(R.id.cardview_quiz2_answer1);
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



        if (intent != null) {
            Lessonnumber = intent.getIntExtra(Tol2_Lesson_Content.LESSONNUMBERINTENT, 1);

            Log.d(TAG, "Quiz onCreate: "+Lessonnumber);
            switch (Lessonnumber)
            {
                case 1:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 2:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 3:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 4:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 5:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 6:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 7:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 8:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 9:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 10:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 11:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 12:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 13:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 14:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 15:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 16:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 17:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 18:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 19:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 20:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 21:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 22:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 23:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 24:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 25:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 26:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
            }
        }






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

        function.CheckAnswer(cardviewquiz1_answer1,cardview_quiz1_answer1_flag,textviewquiz1_answer1,quiz1_rightanswer,cardviewquiz1_answer2,cardviewquiz1_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz1_answer2,cardview_quiz1_answer2_flag,textviewquiz1_answer2,quiz1_rightanswer,cardviewquiz1_answer1,cardviewquiz1_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz1_answer3,cardview_quiz1_answer3_flag,textviewquiz1_answer3,quiz1_rightanswer,cardviewquiz1_answer1,cardviewquiz1_answer2,coordinatorLayout);

        function.CheckAnswer(cardviewquiz2_answer1,cardview_quiz2_answer1_flag,textviewquiz2_answer1,quiz2_rightanswer,cardviewquiz2_answer2,cardviewquiz2_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz2_answer2,cardview_quiz2_answer2_flag,textviewquiz2_answer2,quiz2_rightanswer,cardviewquiz2_answer1,cardviewquiz2_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz2_answer3,cardview_quiz2_answer3_flag,textviewquiz2_answer3,quiz2_rightanswer,cardviewquiz2_answer1,cardviewquiz2_answer2,coordinatorLayout);

        function.CheckAnswer(cardviewquiz3_answer1,cardview_quiz3_answer1_flag,textviewquiz3_answer1,quiz3_rightanswer,cardviewquiz3_answer2,cardviewquiz3_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz3_answer2,cardview_quiz3_answer2_flag,textviewquiz3_answer2,quiz3_rightanswer,cardviewquiz3_answer1,cardviewquiz3_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz3_answer3,cardview_quiz3_answer3_flag,textviewquiz3_answer3,quiz3_rightanswer,cardviewquiz3_answer1,cardviewquiz3_answer2,coordinatorLayout);



        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    int countcheck=0;
                    while (countcheck<3) {
                        if (!cardviewquiz1_answer1.isClickable()) {
                            countcheck++;
                        }
                        if (!cardviewquiz2_answer1.isClickable()) {
                            countcheck++;
                        }
                        if (!cardviewquiz3_answer3.isClickable()) {
                            countcheck++;
                        }
                        if (countcheck == 3){ NextLesson();
                            break;}
                        countcheck=0;
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }

    private void NextLesson() {
        final int exp=5;

        Boolean checkcolor = intent.getBooleanExtra(Sensors.HASCOLOR, true);
        Log.d(TAG, "onDataChange: thuyngocha3 "+checkcolor);
        if(checkcolor)
        {
            mAuth = FirebaseAuth.getInstance();
            String user_id = mAuth.getCurrentUser().getUid();
            final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            current_user_id.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Long value = dataSnapshot.getValue(Long.class);
                    current_user_id.setValue(value.intValue()+5);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

        }
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "All done ", 80000)
                .setAction("Next Lesson", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Tol2_Lesson_Quiz.this,Sensors.class);
                        Log.d("abcdef", "Lesson: "+intent.getIntExtra("MAXBASIC",1));
                        intent1.putExtra("MAXBASIC",intent.getIntExtra("MAXBASIC",1));
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                    }
                });
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#ff669900"));
        snackbar.show();








    }






}
