package com.hatn.learnarduino;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Achievements extends AppIntro {
    // Please DO NOT override onCreate. Use init
    public TextView textView_name1, textView_name2, textView_name3, textView_name4, textView_name5, textView_name6, textView_name7, textView_name8, textView_name9, textView_name10;
    public TextView textView_Description1, textView_Description2, textView_Description3, textView_Description4, textView_Description5, textView_Description6, textView_Description7, textView_Description8, textView_Description9, textView_Description10;


    @Override
    public void init(Bundle savedInstanceState) {

        setContentView(R.layout.activity_achievements);
        ViewGroup v = new Vi
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view1 = inflater.inflate(R.layout.app_achie1, null);
        View view2 = inflater.inflate(R.layout.app_achie2, null);
        View view3 = inflater.inflate(R.layout.app_achie3, null);
        View view4 = inflater.inflate(R.layout.app_achie4, null);
        View view5 = inflater.inflate(R.layout.app_achie5, null);
        View view6 = inflater.inflate(R.layout.app_achie6, null);
        View view7 = inflater.inflate(R.layout.app_achie7, null);
        View view8 = inflater.inflate(R.layout.app_achie8, null);
        View view9 = inflater.inflate(R.layout.app_achie9, null);
        View view10 = inflater.inflate(R.layout.app_achie10, null);
        v.addView(view1);




        textView_Description1 = findViewById(R.id.textview_Achieve1_description);
        textView_Description2 = findViewById(R.id.textview_Achieve2_description);
        textView_Description3 = findViewById(R.id.textview_Achieve3_description);
        textView_Description4 = findViewById(R.id.textview_Achieve4_description);
        textView_Description5 = findViewById(R.id.textview_Achieve5_description);
        textView_Description6 = findViewById(R.id.textview_Achieve6_description);
        textView_Description7 = findViewById(R.id.textview_Achieve7_description);
        textView_Description8 = findViewById(R.id.textview_Achieve8_description);
        textView_Description9 = findViewById(R.id.textview_Achieve9_description);
        textView_Description10 = findViewById(R.id.textview_Achieve10_description);

        textView_name1 = findViewById(R.id.textview_Achieve1_name);
        textView_name2 = findViewById(R.id.textview_Achieve2_name);
        textView_name3 = findViewById(R.id.textview_Achieve3_name);
        textView_name4 = findViewById(R.id.textview_Achieve4_name);
        textView_name5 = findViewById(R.id.textview_Achieve5_name);
        textView_name6 = findViewById(R.id.textview_Achieve6_name);
        textView_name7 = findViewById(R.id.textview_Achieve7_name);
        textView_name8 = findViewById(R.id.textview_Achieve8_name);
        textView_name9 = findViewById(R.id.textview_Achieve9_name);
        textView_name10 = findViewById(R.id.textview_Achieve10_name);


        DatabaseReference AchivementsName1 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement1").child("Name");
        DatabaseReference AchivementsName2 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement2").child("Name");
        DatabaseReference AchivementsName3 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement3").child("Name");
        DatabaseReference AchivementsName4 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement4").child("Name");
        DatabaseReference AchivementsName5 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement5").child("Name");
        DatabaseReference AchivementsName6 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement6").child("Name");
        DatabaseReference AchivementsName7 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement7").child("Name");
        DatabaseReference AchivementsName8 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement8").child("Name");
        DatabaseReference AchivementsName9 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement9").child("Name");
        DatabaseReference AchivementsName10 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement10").child("Name");

        DatabaseReference AchivementsDescription1 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement1").child("Description");
        DatabaseReference AchivementsDescription2 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement2").child("Description");
        DatabaseReference AchivementsDescription3 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement3").child("Description");
        DatabaseReference AchivementsDescription4 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement4").child("Description");
        DatabaseReference AchivementsDescription5 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement5").child("Description");
        DatabaseReference AchivementsDescription6 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement6").child("Description");
        DatabaseReference AchivementsDescription7 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement7").child("Description");
        DatabaseReference AchivementsDescription8 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement8").child("Description");
        DatabaseReference AchivementsDescription9 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement9").child("Description");
        DatabaseReference AchivementsDescription10 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement10").child("Description");

        Function function = new Function();
        function.SetDataIntoObject(AchivementsName1, textView_name1);
        function.SetDataIntoObject(AchivementsName2, textView_name2);
        function.SetDataIntoObject(AchivementsName3, textView_name3);
        function.SetDataIntoObject(AchivementsName4, textView_name4);
        function.SetDataIntoObject(AchivementsName5, textView_name5);
        function.SetDataIntoObject(AchivementsName6, textView_name6);
        function.SetDataIntoObject(AchivementsName7, textView_name7);
        function.SetDataIntoObject(AchivementsName8, textView_name8);
        function.SetDataIntoObject(AchivementsName9, textView_name9);
        function.SetDataIntoObject(AchivementsName10, textView_name10);

        function.SetDataIntoObject(AchivementsDescription1,textView_Description1);
        function.SetDataIntoObject(AchivementsDescription2,textView_Description2);
        function.SetDataIntoObject(AchivementsDescription3,textView_Description3);
        function.SetDataIntoObject(AchivementsDescription4,textView_Description4);
        function.SetDataIntoObject(AchivementsDescription5,textView_Description5);
        function.SetDataIntoObject(AchivementsDescription6,textView_Description6);
        function.SetDataIntoObject(AchivementsDescription7,textView_Description7);
        function.SetDataIntoObject(AchivementsDescription8,textView_Description8);
        function.SetDataIntoObject(AchivementsDescription9,textView_Description9);
        function.SetDataIntoObject(AchivementsDescription10,textView_Description10);



        //adding slides
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie1));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie2));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie3));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie4));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie5));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie6));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie7));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie8));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie9));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_achie10));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);
        showSkipButton(false);

        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        setVibrate(true);
        setVibrateIntensity(30);

        //Add animation to the intro slider
        setDepthAnimation();
    }


    @Override
    public void onNextPressed() {
        // Do something here when users click or tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something here when users click or tap tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something here when slide is changed
    }
}