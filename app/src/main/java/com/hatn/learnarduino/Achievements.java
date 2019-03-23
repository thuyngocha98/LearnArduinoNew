package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    FirebaseAuth mAuth;
    TextView tokenTextView;
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
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
                if (Integer.parseInt(value1)<5)
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
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(Achievements.this, Gettoken.class);
                startActivity(intent);
                return true;

            default:
                onBackPressed();
                return true;
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem alertMenuItem = menu.findItem(R.id.activity_main_menu);

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        LinearLayout rootView = (LinearLayout) alertMenuItem.getActionView();

        tokenTextView = (TextView) rootView.findViewById(R.id.menu_item_number);
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                tokenTextView.setText(value.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}