package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    ArrayAdapter<String> adapter;
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

//        assignData();


//        DatabaseReference AchivementsName1 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement1").child("Name");
//        DatabaseReference AchivementsName2 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement2").child("Name");
//        DatabaseReference AchivementsName3 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement3").child("Name");
//        DatabaseReference AchivementsName4 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement4").child("Name");
//        DatabaseReference AchivementsName5 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement5").child("Name");
//        DatabaseReference AchivementsName6 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement6").child("Name");
//        DatabaseReference AchivementsName7 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement7").child("Name");
//        DatabaseReference AchivementsName8 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement8").child("Name");
//        DatabaseReference AchivementsName9 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement9").child("Name");
//        DatabaseReference AchivementsName10 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement10").child("Name");
//
//        DatabaseReference AchivementsDescription1 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement1").child("Description");
//        DatabaseReference AchivementsDescription2 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement2").child("Description");
//        DatabaseReference AchivementsDescription3 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement3").child("Description");
//        DatabaseReference AchivementsDescription4 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement4").child("Description");
//        DatabaseReference AchivementsDescription5 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement5").child("Description");
//        DatabaseReference AchivementsDescription6 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement6").child("Description");
//        DatabaseReference AchivementsDescription7 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement7").child("Description");
//        DatabaseReference AchivementsDescription8 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement8").child("Description");
//        DatabaseReference AchivementsDescription9 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement9").child("Description");
//        DatabaseReference AchivementsDescription10 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement10").child("Description");
//
//
//        DatabaseReference AchivementsExp1 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement1").child("exp");
//        DatabaseReference AchivementsExp2 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement2").child("exp");
//        DatabaseReference AchivementsExp3 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement3").child("exp");
//        DatabaseReference AchivementsExp4 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement4").child("exp");
//        DatabaseReference AchivementsExp5 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement5").child("exp");
//        DatabaseReference AchivementsExp6 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement6").child("exp");
//        DatabaseReference AchivementsExp7 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement7").child("exp");
//        DatabaseReference AchivementsExp8 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement8").child("exp");
//        DatabaseReference AchivementsExp9 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement9").child("exp");
//        DatabaseReference AchivementsExp10 = FirebaseDatabase.getInstance().getReference().child("Achievements").child("Achievement10").child("exp");
//
//        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
//
//        Function function = new Function();
//        function.SetDataIntoObject(AchivementsName1,textView_name1);
//        function.SetDataIntoObject(AchivementsName2,textView_name2);
//        function.SetDataIntoObject(AchivementsName3,textView_name3);
//        function.SetDataIntoObject(AchivementsName4,textView_name4);
//        function.SetDataIntoObject(AchivementsName5,textView_name5);
//        function.SetDataIntoObject(AchivementsName6,textView_name6);
//        function.SetDataIntoObject(AchivementsName7,textView_name7);
//        function.SetDataIntoObject(AchivementsName8,textView_name8);
//        function.SetDataIntoObject(AchivementsName9,textView_name9);
//        function.SetDataIntoObject(AchivementsName10,textView_name10);
//
//        function.SetDataIntoObject(AchivementsDescription1,textView_Description1);
//        function.SetDataIntoObject(AchivementsDescription2,textView_Description2);
//        function.SetDataIntoObject(AchivementsDescription3,textView_Description3);
//        function.SetDataIntoObject(AchivementsDescription4,textView_Description4);
//        function.SetDataIntoObject(AchivementsDescription5,textView_Description5);
//        function.SetDataIntoObject(AchivementsDescription6,textView_Description6);
//        function.SetDataIntoObject(AchivementsDescription7,textView_Description7);
//        function.SetDataIntoObject(AchivementsDescription8,textView_Description8);
//        function.SetDataIntoObject(AchivementsDescription9,textView_Description9);
//        function.SetDataIntoObject(AchivementsDescription10,textView_Description10);
//
//        function.SetDataIntoObject(AchivementsExp1, tvHint1 );
//        function.SetDataIntoObject(AchivementsExp2, tvHint2 );
//        function.SetDataIntoObject(AchivementsExp3, tvHint3 );
//        function.SetDataIntoObject(AchivementsExp4, tvHint4 );
//        function.SetDataIntoObject(AchivementsExp5, tvHint5 );
//        function.SetDataIntoObject(AchivementsExp6, tvHint6 );
//        function.SetDataIntoObject(AchivementsExp7, tvHint7 );
//        function.SetDataIntoObject(AchivementsExp8, tvHint8 );
//        function.SetDataIntoObject(AchivementsExp9, tvHint9 );
//        function.SetDataIntoObject(AchivementsExp10, tvHint10 );
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        mAuth = FirebaseAuth.getInstance();
//        String user_id = mAuth.getCurrentUser().getUid();
//        final DatabaseReference db_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
//        db_user.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int value = Integer.parseInt(dataSnapshot.getValue().toString());
//                if(value >= Integer.parseInt(tvHint1.getText().toString()))
//                {
//                    media_image1.setImageResource(R.color.right);
//                    check1.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint2.getText().toString()))
//                {
//                    media_image2.setImageResource(R.color.right);
//                    check2.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint3.getText().toString()))
//                {
//                    media_image3.setImageResource(R.color.right);
//                    check3.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint4.getText().toString()))
//                {
//                    media_image4.setImageResource(R.color.right);
//                    check4.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint5.getText().toString()))
//                {
//                    media_image5.setImageResource(R.color.right);
//                    check5.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint6.getText().toString()))
//                {
//                    media_image6.setImageResource(R.color.right);
//                    check6.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint7.getText().toString()))
//                {
//                    media_image7.setImageResource(R.color.right);
//                    check7.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint8.getText().toString()))
//                {
//                    media_image8.setImageResource(R.color.right);
//                    check8.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint9.getText().toString()))
//                {
//                    media_image9.setImageResource(R.color.right);
//                    check9.setImageResource(R.drawable.ic_checked_right);
//                }
//                if(value >= Integer.parseInt(tvHint10.getText().toString()))
//                {
//                    media_image10.setImageResource(R.color.right);
//                    check10.setImageResource(R.drawable.ic_checked_right);
//                }
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }

//        });

//        achievements =new LinkedList<>();
//        for (int i = 1 ;i<100;i++)
//        {
//            achievements.add(new achievement("Achievement " + i,"ádfasdfasdfasdfasfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasddf",30));
//        }
//        ListView listView;
//        listView = findViewById(R.id.id_listview);
//        Adapter_Achievements achievement_adapter =new Adapter_Achievements(R.layout.listview_achievements_item,achievements);
//        listView.setAdapter(achievement_adapter);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        lvAchieve=findViewById(R.id.id_listview);
        lvAchieve.setAdapter(adapter);
//lấy đối tượng FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//Kết nối tới node có tên là contacts (node này do ta định nghĩa trong CSDL Firebase)
        DatabaseReference myRef = database.getReference("Achievements");
//truy suất và lắng nghe sự thay đổi dữ liệu
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//vòng lặp để lấy dữ liệu khi có sự thay đổi trên Firebase
                for (DataSnapshot data: dataSnapshot.getChildren())
                {
//lấy key của dữ liệu
                    String key=data.getKey();
//lấy giá trị của key (nội dung)
                    String value=data.getValue().toString();
                    adapter.add(key+"\n"+value);
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
}
