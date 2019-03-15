package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class Achievements extends AppCompatActivity {

    public TextView textView_name1, textView_name2, textView_name3, textView_name4, textView_name5, textView_name6, textView_name7, textView_name8, textView_name9, textView_name10;
    public TextView textView_Description1, textView_Description2, textView_Description3, textView_Description4, textView_Description5, textView_Description6, textView_Description7, textView_Description8, textView_Description9, textView_Description10;
    FirebaseAuth mAuth;
    public TextView tvHint1, tvHint2, tvHint3, tvHint4, tvHint5, tvHint6, tvHint7, tvHint8, tvHint9, tvHint10;
    public ImageView media_image1,media_image2,media_image3,media_image4,media_image5,media_image6,media_image7,media_image8,media_image9,media_image10;
    ProgressDialog progressDialog;
    public ImageView check1, check2, check3, check4, check5, check6, check7, check8, check9, check10;

    ListView lvAchieve;
    Adapter_Achievements adapter;
    private LinkedList<achievement> achievements;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvAchieve=findViewById(R.id.lvAchievements);
        adapter=new Adapter_Achievements(this,R.layout.listview_achievements_item);
        lvAchieve.setAdapter(adapter);


//        adapter.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Achievements");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren())
                {
                    achievement m_achievement = data.getValue(achievement.class);
                    String key = data.getKey();
                    m_achievement.setAchivementID(key);
                    adapter.add(m_achievement);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });







    }

    public void assignData(){
//        textView_name1 = (TextView) findViewById(R.id.textview_A_name1);
//        textView_name2 = (TextView) findViewById(R.id.textview_A_name2);
//        textView_name3 = (TextView) findViewById(R.id.textview_A_name3);
//        textView_name4 = (TextView) findViewById(R.id.textview_A_name4);
//        textView_name5 = (TextView) findViewById(R.id.textview_A_name5);
//        textView_name6 = (TextView) findViewById(R.id.textview_A_name6);
//        textView_name7 = (TextView) findViewById(R.id.textview_A_name7);
//        textView_name8 = (TextView) findViewById(R.id.textview_A_name8);
//        textView_name9 = (TextView) findViewById(R.id.textview_A_name9);
//        textView_name10 = (TextView) findViewById(R.id.textview_A_name10);
//
//        textView_Description1 = (TextView) findViewById(R.id.textview_A_description1);
//        textView_Description2 = (TextView) findViewById(R.id.textview_A_description2);
//        textView_Description3 = (TextView) findViewById(R.id.textview_A_description3);
//        textView_Description4 = (TextView) findViewById(R.id.textview_A_description4);
//        textView_Description5 = (TextView) findViewById(R.id.textview_A_description5);
//        textView_Description6 = (TextView) findViewById(R.id.textview_A_description6);
//        textView_Description7 = (TextView) findViewById(R.id.textview_A_description7);
//        textView_Description8 = (TextView) findViewById(R.id.textview_A_description8);
//        textView_Description9 = (TextView) findViewById(R.id.textview_A_description9);
//        textView_Description10 = (TextView) findViewById(R.id.textview_A_description10);
//
//        tvHint1 = (TextView) findViewById(R.id.tv_hint1);
//        tvHint2 = (TextView) findViewById(R.id.tv_hint2);
//        tvHint3 = (TextView) findViewById(R.id.tv_hint3);
//        tvHint4 = (TextView) findViewById(R.id.tv_hint4);
//        tvHint5 = (TextView) findViewById(R.id.tv_hint5);
//        tvHint6 = (TextView) findViewById(R.id.tv_hint6);
//        tvHint7 = (TextView) findViewById(R.id.tv_hint7);
//        tvHint8 = (TextView) findViewById(R.id.tv_hint8);
//        tvHint9 = (TextView) findViewById(R.id.tv_hint9);
//        tvHint10 = (TextView) findViewById(R.id.tv_hint10);
//
//        media_image1 = (ImageView) findViewById(R.id.media_image1);
//        media_image2 = (ImageView) findViewById(R.id.media_image2);
//        media_image3 = (ImageView) findViewById(R.id.media_image3);
//        media_image4 = (ImageView) findViewById(R.id.media_image4);
//        media_image5 = (ImageView) findViewById(R.id.media_image5);
//        media_image6 = (ImageView) findViewById(R.id.media_image6);
//        media_image7 = (ImageView) findViewById(R.id.media_image7);
//        media_image8 = (ImageView) findViewById(R.id.media_image8);
//        media_image9 = (ImageView) findViewById(R.id.media_image9);
//        media_image10 = (ImageView) findViewById(R.id.media_image10);
//
//        check1 = (ImageView) findViewById(R.id.img_archie1);
//        check2 = (ImageView) findViewById(R.id.img_archie2);
//        check3 = (ImageView) findViewById(R.id.img_archie3);
//        check4 = (ImageView) findViewById(R.id.img_archie4);
//        check5 = (ImageView) findViewById(R.id.img_archie5);
//        check6 = (ImageView) findViewById(R.id.img_archie6);
//        check7 = (ImageView) findViewById(R.id.img_archie7);
//        check8 = (ImageView) findViewById(R.id.img_archie8);
//        check9 = (ImageView) findViewById(R.id.img_archie9);
//        check10 = (ImageView) findViewById(R.id.img_archie10);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}