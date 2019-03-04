package com.hatn.learnarduino.Tol3;

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
import com.hatn.learnarduino.LED;
import com.hatn.learnarduino.R;
import com.hatn.learnarduino.Sensors;

public class Tol3_Lesson_Quiz extends AppCompatActivity {

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
    int experience;
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

//        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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




        DatabaseReference Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
        DatabaseReference Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
        DatabaseReference Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

        DatabaseReference Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
        DatabaseReference Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
        DatabaseReference Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
        DatabaseReference Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

        DatabaseReference Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
        DatabaseReference Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
        DatabaseReference Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
        DatabaseReference Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

        DatabaseReference Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
        DatabaseReference Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
        DatabaseReference Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
        DatabaseReference Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");



        if (intent != null) {
            Lessonnumber = intent.getIntExtra(Tol3_Lesson_Content.LESSONNUMBERINTENT, 1);

            Log.d(TAG, "Quiz onCreate: "+Lessonnumber);
            switch (Lessonnumber)
            {
                case 1:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson1").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 2:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson2").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 3:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson3").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 4:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson4").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 5:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson5").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 6:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson6").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 7:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson7").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 8:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson8").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 9:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson9").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 10:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson10").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 11:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson11").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 12:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson12").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 13:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson13").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 14:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson14").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 15:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson15").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 16:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson16").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 17:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson17").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 18:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson18").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 19:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson19").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 20:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson20").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 21:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson21").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 22:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson22").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 23:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson23").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 24:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson24").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 25:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson25").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

                    break;
                }
                case 26:
                {
                    Getquiz1question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Quiz");
                    Getquiz2question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Quiz");
                    Getquiz3question = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Quiz");

                    Getquiz1rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer");
                    Getquiz1answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer1");
                    Getquiz1answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer2");
                    Getquiz1answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz1").child("Answer3");

                    Getquiz2rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer");
                    Getquiz2answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer1");
                    Getquiz2answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer2");
                    Getquiz2answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz2").child("Answer3");

                    Getquiz3rightanswer = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer");
                    Getquiz3answer1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer1");
                    Getquiz3answer2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer2");
                    Getquiz3answer3 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Lesson26").child("Content").child("ContentQuiz").child("ContentQuiz3").child("Answer3");

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

//        cardviewquiz1_answer1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("check true", "onClick: "+quiz1_rightanswer.getText().toString());
//                Log.d("check true", "onClick: "+textviewquiz1_answer1.getText().toString());
//
//                if (textviewquiz1_answer1.getText().toString().equals(quiz1_rightanswer.getText().toString()))
//                {
//                    RightAnswer(cardview_quiz1_answer1_flag);
//                    cardviewquiz1_answer1.setClickable(false);
//                    cardviewquiz1_answer2.setClickable(false);
//                    cardviewquiz1_answer3.setClickable(false);
//                }
//                else WrongAnswer(cardview_quiz1_answer1_flag);
//            }
//        });

        function.CheckAnswer(cardviewquiz1_answer1,cardview_quiz1_answer1_flag,textviewquiz1_answer1,quiz1_rightanswer,cardviewquiz1_answer2,cardviewquiz1_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz1_answer2,cardview_quiz1_answer2_flag,textviewquiz1_answer2,quiz1_rightanswer,cardviewquiz1_answer1,cardviewquiz1_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz1_answer3,cardview_quiz1_answer3_flag,textviewquiz1_answer3,quiz1_rightanswer,cardviewquiz1_answer1,cardviewquiz1_answer2,coordinatorLayout);

        function.CheckAnswer(cardviewquiz2_answer1,cardview_quiz2_answer1_flag,textviewquiz2_answer1,quiz2_rightanswer,cardviewquiz2_answer2,cardviewquiz2_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz2_answer2,cardview_quiz2_answer2_flag,textviewquiz2_answer2,quiz2_rightanswer,cardviewquiz2_answer1,cardviewquiz2_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz2_answer3,cardview_quiz2_answer3_flag,textviewquiz2_answer3,quiz2_rightanswer,cardviewquiz2_answer1,cardviewquiz2_answer2,coordinatorLayout);

        function.CheckAnswer(cardviewquiz3_answer1,cardview_quiz3_answer1_flag,textviewquiz3_answer1,quiz3_rightanswer,cardviewquiz3_answer2,cardviewquiz3_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz3_answer2,cardview_quiz3_answer2_flag,textviewquiz3_answer2,quiz3_rightanswer,cardviewquiz3_answer1,cardviewquiz3_answer3,coordinatorLayout);
        function.CheckAnswer(cardviewquiz3_answer3,cardview_quiz3_answer3_flag,textviewquiz3_answer3,quiz3_rightanswer,cardviewquiz3_answer1,cardviewquiz3_answer2,coordinatorLayout);



//
//        }
//
//        if(countcheck==3)
//        {
//            NextLesson();
//        }



//        Thread t = new Thread();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                int countcheck=0;
//
//                while (countcheck<3) {
//                    if (!cardviewquiz1_answer1.isClickable()) {
//                        countcheck++;
//                    }
//                    if (!cardviewquiz2_answer1.isClickable()) {
//                        countcheck++;
//                    }
//                    if (!cardviewquiz3_answer3.isClickable()) {
//                        countcheck++;
//                    }
//                    if (countcheck == 3){ NextLesson();}
//                    countcheck=0;
//                }
//            }
//        },1000);

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

//        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int countemp=0;
//                Log.d(TAG, "Card color: "+cardview_quiz1_answer1_flag.getCardBackgroundColor().toString());
//
//                if (!cardviewquiz1_answer1.isClickable())
//                {
//                    countemp++;
//                }
//                if (!cardviewquiz2_answer1.isClickable())
//                {
//                    countemp++;
//                }
//                if (!cardviewquiz3_answer3.isClickable())
//                {
//                    countemp++;
//                }
//
//                if(countemp!=3)
//                {
//                    Snackbar snackbar = Snackbar
//                            .make(coordinatorLayout, "Please answer all questions before moving to next lesson ", Snackbar.LENGTH_LONG);
//                            View snackbarView = snackbar.getView();
//                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                            textView.setTextColor(Color.parseColor("#ffa000"));
//                            snackbar.show();
//                    snackbar.show();
//                }else {
//                    NextLesson();
//
//                }
//            }
//        });










    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Tol3_Lesson_Quiz.this, Tol3_Lesson_Content.class));
        finish();

    }




    private void thread(){
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
            if (countcheck == 3){ NextLesson();}
            countcheck=0;
        }
    }



    private void NextLesson() {
        final int exp=5;
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "All done ", 80000)
                .setAction("Next Lesson", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Tol3_Lesson_Quiz.this,LED.class);
                        intent.putExtra("Coloredcard", exp);
                        startActivity(intent);
                    }
                });
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#ff669900"));
        snackbar.show();

        //TODO: get exp from database, push exp to database, change color of CardView

        Boolean checkcolor = intent.getBooleanExtra(Sensors.HASCOLOR, false);
        if(!checkcolor)
        {
            mAuth = FirebaseAuth.getInstance();

//        Function function =new Function();
//        function.SetDataIntoObject(Userexp,experience);
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
                    // Failed to read value

                }
            });
        }



        //current_user_id.setValue(value+5);




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
