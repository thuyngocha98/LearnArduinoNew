package com.hatn.learnarduino;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Gettoken extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    CoordinatorLayout coordinatorLayout;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettoken);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.media_image2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(Gettoken.this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        progressDialog= ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
        loadRewardedVideoAd();

        Button active = findViewById(R.id.action_button_1);
        Button view_video = findViewById(R.id.action_button_1_2);
        Button share = findViewById(R.id.action_button_1_3);
        Button check_in = findViewById(R.id.action_button_1_4);
        final TextView number_of_token = findViewById(R.id.number_of_token);

        coordinatorLayout = findViewById(R.id.gettoken_layout);

        mAuth = FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                number_of_token.setText(value.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        final TextView pro_version =  new TextView(this);
        final DatabaseReference pro_version_check = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Pro");
        pro_version_check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                pro_version.setText(""+value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(number_of_token.getText().toString())>=500&&Integer.parseInt(pro_version.getText().toString())==0)
                {
                    //TODO: remove ads
                    DatabaseReference pro_version = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Pro");
                    pro_version.setValue(1);
                    DatabaseReference token = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
                    int temp = Integer.parseInt(number_of_token.getText().toString());
                    token.setValue(temp-500);
                } else if (Integer.parseInt(pro_version.getText().toString())==1)
                {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "You have already unlocked no-ads version ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    DatabaseReference pro_version = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Pro");
                    pro_version.setValue(0);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "You don't have enough tokens to unlock no-ads version ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

        view_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "I got something to share you");
                i.putExtra(Intent.EXTRA_TEXT, "Check out this app http://www.url.com");
                startActivityForResult(Intent.createChooser(i,"Share this app to friend"), 123);
            }
        });
        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLoginDay();
            }
        });
        RemoveAd();
//        CheckLoginDay();

    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 123) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mAuth = FirebaseAuth.getInstance();
                String user_id = mAuth.getCurrentUser().getUid();
                final DatabaseReference current_user_id_token = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
                current_user_id_token.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        Long value = dataSnapshot.getValue(Long.class);
                        current_user_id_token.setValue(value.intValue() + 6);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "You got 6 tokens ", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Sharing failed ", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
    @Override
    public void onRewarded(RewardItem reward) {
        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id_token = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id_token.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                current_user_id_token.setValue(value.intValue() + 10);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        loadRewardedVideoAd();
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "You got 10 tokens ", Snackbar.LENGTH_LONG);
        snackbar.show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
//        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
//        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "Please check your connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
//        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

    private void RemoveAd()
    {

        {
            final TextView proversion = new TextView(this);
            proversion.setText("0");
            mAuth = FirebaseAuth.getInstance();

            if (mAuth.getCurrentUser()!=null)
            {
                String user_id1= mAuth.getCurrentUser().getUid();
                final DatabaseReference pro_version_check = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Pro");
                pro_version_check.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        int value = dataSnapshot.getValue(Integer.class);
                        Log.d("pro", "onDataChange: pro check 1 "+ value);
                        proversion.setText(value+"");

                        int temp = Integer.parseInt(proversion.getText().toString());
                        Log.d("pro", "onDataChange: pro check 2 "+ temp);
                        if (temp==1)
                        {
                            mAdView.setEnabled(false);
                            mAdView.setVisibility(View.GONE);
                        } else {
                            mAdView.setEnabled(true);
                            mAdView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
            }
        }
    }

    private void CheckLoginDay()
    {
        final Date date = new Date();
        final Date newDate = new Date(date.getTime());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        final String stringdate = dt.format(newDate);
        Log.d("Date:", "CheckLoginDay: "+stringdate);

        if (mAuth.getCurrentUser()!=null)
        {
            final String user_id1= mAuth.getCurrentUser().getUid();
            final DatabaseReference last_login = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("LastLogin");
            last_login.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    String date = dataSnapshot.getValue(String.class);
                    if (!stringdate.equals(date)) {
                        final DatabaseReference current_user_id_token = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Token");
                        current_user_id_token.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                Long value = dataSnapshot.getValue(Long.class);
                                current_user_id_token.setValue(value.intValue() + 10);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value

                            }
                        });
                        loadRewardedVideoAd();
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "You got 10 tokens for daily login", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        // Reward the user.

                        last_login.setValue(stringdate);
                        // Set today is the last login day
                    }
                    else {
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "You have already got daily login reward", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
    }
}
