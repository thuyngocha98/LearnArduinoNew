package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
        final TextView exptemp= findViewById(R.id.textv_exp_temp);
        adapter=new Adapter_Achievements(this,R.layout.listview_achievements_item);
        lvAchieve.setAdapter(adapter);


//        adapter.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Achievements");

        mAuth=FirebaseAuth.getInstance();
        String user_id2 = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_id2 = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id2).child("Exp");
        current_user_id2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.getValue().toString();
                exptemp.setText(value1);
                if (Integer.parseInt(value1)<20)
                {
                    new AlertDialog.Builder(Achievements.this)
                            .setTitle("Sorry")
                            .setMessage("You haven't completed any achievements yet, please come back later")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onBackPressed();
                                }
                            })
                            .show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren())
                {
                    achievement m_achievement = data.getValue(achievement.class);
                    int exp = m_achievement.getExp();
                    String key = data.getKey();
                    m_achievement.setAchivementID(key);


                    if (Integer.parseInt(exptemp.getText().toString())>=exp)
                    {
                        adapter.add(m_achievement);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}