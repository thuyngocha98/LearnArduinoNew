package com.hatn.learnarduino.Tol4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Gettoken;
import com.hatn.learnarduino.Movement;
import com.hatn.learnarduino.R;

public class Tol4_MainContent extends AppCompatActivity {

    ImageButton expand1, expand2, expand3;
    TextView textView_subtext1, textView_discription1, textView_subtext2, textView_subtext3, textView_discription2, textView_discription3;
    TextView tokenTextView;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tol4__main_content);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expand1 = findViewById(R.id.expand_button);
        textView_subtext1=findViewById(R.id.sub_text);
        textView_discription1=findViewById(R.id.supporting_text);

        expand2 = findViewById(R.id.expand_button_2);
        textView_subtext2 = findViewById(R.id.sub_text2);
        textView_discription2 = findViewById(R.id.supporting_text2);

        expand3 = findViewById(R.id.expand_button_3);
        textView_subtext3 = findViewById(R.id.sub_text3);
        textView_discription3 = findViewById(R.id.supporting_text3);

        ExpandButton(expand1,textView_discription1,textView_subtext1);
        ExpandButton(expand2,textView_discription2,textView_subtext2);
        ExpandButton(expand3,textView_discription3,textView_subtext3);

    }
    void ExpandButton(final ImageButton expand, final TextView textView_discription, TextView textView_subtext1)
    {
        textView_discription.setVisibility(View.GONE);
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0)
                {
                    textView_discription.setVisibility(View.VISIBLE);
                    expand.setImageResource(R.drawable.ic_up);
                    flag=1;
                }
                else if (flag==1)
                {
                    textView_discription.setVisibility(View.GONE);
                    expand.setImageResource(R.drawable.ic_down);
                    flag=0;
                }

            }
        });

        textView_subtext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand.callOnClick();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(Movement.this, MainActivity.class));
        finish();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(this, Gettoken.class);
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

    private void ButtonLesson(CardView button, final int value, final boolean hascolor)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Movement.this, Tol4_Lesson_Content.class);
//                i.putExtra("LESSONNUMBERINTENT",value);
//                Log.d(TAG, "test onClick: "+value);
//                i.putExtra("HASCOLOR", hascolor);
//                i.putExtra("MAXLED", max_led);
                Intent i = new Intent(getApplicationContext(), Tol4_MainContent.class);
                startActivity(i);
            }
        });
    }
}
