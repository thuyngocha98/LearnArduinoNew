package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tapadoo.alerter.Alerter;

import java.util.Arrays;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private static final int RC_SIGN_IN = 123;
    public FirebaseAuth mAuth;
    int Token = 0;
    int numberTotalContent = 6;
    int max_exp = 0;
    public int experience;
    private DrawerLayout drawerLayout;
    ImageButton buttonBasic, buttonSensors, buttonLED, buttonMovement, buttonTol5, buttonTol6;
    ProgressDialog progressDialog;
    private String email;
    ProgressBar progressBarSensor, progressBarLed, progressBarBasic, progressBarMovement, progressBarExp;
    MenuItem nav_item1, nav_item2, nav_item3, nav_item4;
    TextView tvCheckWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        progressBarSensor = (ProgressBar) findViewById(R.id.progressBarSensors);
        progressBarBasic = (ProgressBar) findViewById(R.id.progressBarBasic);
        progressBarLed = (ProgressBar) findViewById(R.id.progressBarLED);
        progressBarMovement = (ProgressBar) findViewById(R.id.progressBarMovement);
        tvCheckWelcome = (TextView) findViewById(R.id.tv_temp_check_welcome);


        MobileAds.initialize(this, "ca-app-pub-1398912587505329~4968336940");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(MainActivity.this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();



        //navigation drawer bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //progessOverlay.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        // Create layout with number of type of lesson
        DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Number_Of_Type_Of_Lesson");
        number1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());

                int[] Layout_hint = {
                        R.id.linear_btn1,
                        R.id.linear_btn2,
                        R.id.linear_btn3,
                        R.id.linear_btn4,
                        R.id.linear_btn5,
                        R.id.linear_btn6,
                };

                for(int i = value; i < numberTotalContent; i++){
                    LinearLayout temp = findViewById(Layout_hint[i]);
                    temp.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        mAuth = FirebaseAuth.getInstance();
        // login firebaseUI
        if(mAuth.getCurrentUser() != null){
            setProfile();

            loadingProgressBarTotal();
            //load screen welcome
//            Intent intent = new Intent(MainActivity.this, Welcome.class);
//            intent.putExtra("TypeofSlider", 2);
//            startActivity(intent);

        }else {
            functionLogin();
        }



//        loadingProgressBarTotal();

        //Merge content
        buttonBasic = findViewById(R.id.btn_basic);
        buttonSensors=findViewById(R.id.btn_sensors);
        buttonLED=findViewById(R.id.btn_LED);
        buttonMovement=findViewById(R.id.btn_movement);
        buttonTol5 = findViewById(R.id.btn_tol5);
        buttonTol6 = findViewById(R.id.btn_tol6);



        buttonBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Basic.class);
                startActivity(i);
            }
        });

//        buttonSensors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,Sensors.class);
//                i.putExtra("MAXBASIC2", progressBarBasic.getMax());
//                startActivity(i);
//            }
//        });

//        buttonLED.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,LED.class);
//                i.putExtra("MAXSENSOR2", (progressBarBasic.getMax()+progressBarSensor.getMax()));
//                startActivity(i);
//            }
//        });

//        buttonMovement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,Movement.class);
//                i.putExtra("MAXLED2", (progressBarLed.getMax()+progressBarBasic.getMax()+progressBarSensor.getMax()));
//                startActivity(i);
//            }
//        });
        buttonTol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Tol5.class);
                startActivity(i);
            }
        });

        buttonTol6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Tol6.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "onRewarded! exp: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

    //Merge content
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_Basic){
            Intent i = new Intent(this, Basic.class);
            startActivity(i);
        }
        else if (id == R.id.nav_aboutus) {
            Intent i = new Intent(this, Aboutus.class);
            startActivity(i);
        } else if (id == R.id.nav_achievements){
            Intent i = new Intent(this, Achievements.class);
            startActivity(i);
        }
        else if (id == R.id.nav_moreapps) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Cherala+Apps"));
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            logOut();
        } else if (id == R.id.nav_feedback) {
            Intent i = new Intent(this, Feedback.class);
            i.putExtra("Name", mAuth.getCurrentUser().getDisplayName());
            startActivity(i);
        } else if (id == R.id.nav_remove_ads) {
            Intent i = new Intent(this, Gettoken.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    // set avatar and information user
    public void setProfile(){

        String name = "unidentified";
        email = "";
        Uri uriImage;
        String image = "";
        try {
            name = mAuth.getCurrentUser().getDisplayName();
            email = mAuth.getCurrentUser().getEmail();
            uriImage = mAuth.getCurrentUser().getPhotoUrl();

            if(uriImage != null){
                image = mAuth.getCurrentUser().getPhotoUrl().toString();
            }
        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();
        nav_item1 = menuNav.findItem(R.id.nav_Basic);
        nav_item2 = menuNav.findItem(R.id.nav_Sensors);
        nav_item3 = menuNav.findItem(R.id.nav_LED);
        nav_item4 = menuNav.findItem(R.id.nav_Movement);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.tv_header_name);
        navUsername.setText(name);
        TextView navEmail = headerView.findViewById(R.id.tv_header_email);
        navEmail.setText(email);
        progressBarExp = headerView.findViewById(R.id.progressBar_exp);
//        progressBarExp.setMax(max_exp);
        final TextView navToken = headerView.findViewById(R.id.tv_header_token);
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                navToken.setText(value.toString());
                progressBarExp.setProgress(Integer.parseInt(value.toString()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        final ImageView navImage = headerView.findViewById(R.id.img_header);
        if(!TextUtils.isEmpty(image)){
            Picasso.get().load(image).resize(75, 75).centerCrop().transform(new CropCircleTransformation()).into(navImage);
        }
        else {
            navImage.setImageResource(R.drawable.user_logo);
        }


        if (isOnline()) {
            Alerter.create(MainActivity.this)
                    .setTitle("Loading...")
                    .setText("Updating content and lesson")
                    .setIcon(R.drawable.ic_loading)
                    .enableProgress(true)
                    .setProgressColorRes(R.color.lime)
                    .setDuration(1000)
                    .setBackgroundColorRes(R.color.alert_background) // or setBackgroundColorInt(Color.CYAN)
                    .show();
            //loadingProgressBarTotal();
            Snackbar snackbarz = Snackbar
                    .make(drawerLayout, "Signed in as " + email, 1200)
                    .setAction("LOG OUT", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            logOut();
                        }
                    });
            enableViews(drawerLayout, true);
            snackbarz.show();
//            enableViews(drawerLayout, false);


//            Snackbar snackbar = Snackbar
//                    .make(drawerLayout, "Signed in as " + email, 1200)
//                    .setAction("LOG OUT", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            logOut();
//                        }
//                    });
//            enableViews(drawerLayout, true);
//            snackbar.show();
        }
//        } else {
//
//            enableViews(drawerLayout, false);
//
//            Snackbar snackbar = Snackbar
//                    .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", 100000);
//
//            View snackbarView = snackbar.getView();
//            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//            textView.setTextColor(Color.RED);
//            snackbar.setAction("Try again", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (isOnline())
//                    {
//                        enableViews(drawerLayout,true);
//                    }
//                    else {
//                        Snackbar snackbar = Snackbar
//                                .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", 8000);
//
//                        View snackbarView = snackbar.getView();
//                        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                        textView.setTextColor(Color.RED);
//                        snackbar.show();
//                    }
//                }
//            });
//            snackbar.show();
    }

    // login with firebaseUI
    public  void functionLogin(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());
        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.cir_logo)      // Set logo drawable
                        .setTheme(R.style.AppTheme)      // Set theme
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Log.d("xxxx", "onResult");
                setProfile();
                readData();
                //readToken();
                //load screen welcome
//                Intent intent = new Intent(MainActivity.this, Welcome.class);
//                intent.putExtra("TypeofSlider", 1);
//                startActivity(intent);

            } else {
                //Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
                if (response == null) {
                    Snackbar snackbar = Snackbar
                            .make(drawerLayout, "Sign in cancelled ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar snackbar = Snackbar
                            .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("Exit", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }
                                    });
                                    snackbar.show();
                    enableViews(drawerLayout,false);
                    return;
                }
                Snackbar snackbar = Snackbar
                        .make(drawerLayout, "Unknown error occurred, please contact support in the about us page if this problem persists  ", Snackbar.LENGTH_LONG);
                snackbar.show();


            }
        }
    }

    private void logOut(){

        if (isOnline()) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // ...
                            if(mAuth.getCurrentUser() != null)
                                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            else{
                                Snackbar snackbar = Snackbar
                                        .make(drawerLayout, "Signed out " + email, Snackbar.LENGTH_LONG);
                                snackbar.show();
                                functionLogin();
                            }

                        }
                    });
        }
        else if (!isOnline()) {
            Snackbar snackbar = Snackbar
                    .make(drawerLayout, "Can't sign out because you don't have internet connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else  {
            Snackbar snackbar = Snackbar
                    .make(drawerLayout, "Unknown occurred, please contact support in the about us page if this problem persists  ", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        tvCheckWelcome.setText("1");

    }

    private void enableViews(View v, boolean enabled) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0;i<vg.getChildCount();i++) {
                enableViews(vg.getChildAt(i), enabled);
            }
        }
        v.setEnabled(enabled);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    // experience and token user
    public void setValueExperience(){

        tvCheckWelcome.setText("0");

        String user_id1 = mAuth.getCurrentUser().getUid();

        DatabaseReference current_user_id1 = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Exp");
        current_user_id1.setValue(0);
        DatabaseReference token_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Token");
        token_user.setValue(0);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadingProgressBarTotal();
        Intent intent = new Intent(MainActivity.this, Welcome.class);
        intent.putExtra("TypeofSlider", 1);
        startActivity(intent);


    }



    // read data user
    public  void readData(){
        String user_id2 = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_id2 = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id2).child("Exp");
        current_user_id2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value1 = dataSnapshot.getValue(Long.class);
                if(value1 == null) {
                    setValueExperience();
                }

                int checkWelcome = Integer.parseInt(tvCheckWelcome.getText().toString());

                if(checkWelcome == 1){

                    tvCheckWelcome.setText(value1.toString());
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    intent.putExtra("TypeofSlider", 2);
                    startActivity(intent);

                    loadingProgressBarTotal();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Get user data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProgressBarMain(){
        //set progress progressbar
        String progressbar_user_id = mAuth.getCurrentUser().getUid();
        Log.d("tag","checkprogressbar: uid " + progressbar_user_id);
        DatabaseReference progressbar_user = FirebaseDatabase.getInstance().getReference().child("Users").child(progressbar_user_id).child("Exp");
        progressbar_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("tag","checkprogressbar: get value");
                int value = Integer.parseInt(dataSnapshot.getValue().toString());

                int maxBasic = progressBarBasic.getMax();
                int maxSensor = progressBarSensor.getMax();
                int maxLed = progressBarLed.getMax();
                int maxMovement = progressBarMovement.getMax();

                progressBarBasic.setProgress(0);
                progressBarSensor.setProgress(0);
                progressBarLed.setProgress(0);
                progressBarMovement.setProgress(0);

                buttonBasic.setBackgroundResource(R.drawable.rounded_button);
                buttonSensors.setBackgroundResource(R.drawable.rounded_button);
                buttonLED.setBackgroundResource(R.drawable.rounded_button);
                buttonMovement.setBackgroundResource(R.drawable.rounded_button);

//                progressBarBasic.setVisibility(View.VISIBLE);
//                progressBarSensor.setVisibility(View.VISIBLE);
//                progressBarLed.setVisibility(View.VISIBLE);
//                progressBarMovement.setVisibility(View.VISIBLE);



                if(value >= maxBasic){
                    // set click button sensor when exp reasonable
                    buttonSensors.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this,Sensors.class);
                            i.putExtra("MAXBASIC2", progressBarBasic.getMax());
                            startActivity(i);
                        }
                    });
                    nav_item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Intent i = new Intent(MainActivity.this,Sensors.class);
                            i.putExtra("MAXBASIC2", progressBarBasic.getMax());
                            startActivity(i);
                            return false;
                        }
                    });

                    //enable and disable nav item
                    progressBarBasic.setProgress(maxBasic);
                    buttonSensors.setEnabled(true);
                    buttonBasic.setBackgroundResource(R.drawable.rounded_button_green);

                    if(value >= (maxBasic+maxSensor)){
                        // set click button led when exp reasonable
                        buttonLED.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(MainActivity.this,LED.class);
                                i.putExtra("MAXSENSOR2", (progressBarBasic.getMax()+progressBarSensor.getMax()));
                                startActivity(i);
                            }
                        });
                        nav_item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent i = new Intent(MainActivity.this,LED.class);
                                i.putExtra("MAXSENSOR2", (progressBarBasic.getMax()+progressBarSensor.getMax()));
                                startActivity(i);
                                return false;
                            }
                        });

                        progressBarSensor.setProgress(maxSensor);
                        buttonLED.setEnabled(true);
                        buttonBasic.setBackgroundResource(R.drawable.rounded_button_green);
                        buttonSensors.setBackgroundResource(R.drawable.rounded_button_green);

                        if(value >= (maxBasic+maxSensor+maxLed)){
                            // set click button movement when exp reasonable
                            buttonMovement.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(MainActivity.this,Movement.class);
                                    i.putExtra("MAXLED2", (progressBarLed.getMax()+progressBarBasic.getMax()+progressBarSensor.getMax()));
                                    startActivity(i);
                                }
                            });
                            nav_item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    Intent i = new Intent(MainActivity.this,Movement.class);
                                    i.putExtra("MAXLED2", (progressBarLed.getMax()+progressBarBasic.getMax()+progressBarSensor.getMax()));
                                    startActivity(i);
                                    return false;
                                }
                            });

                            progressBarLed.setProgress(maxLed);
                            buttonMovement.setEnabled(true);
                            buttonBasic.setBackgroundResource(R.drawable.rounded_button_green);
                            buttonSensors.setBackgroundResource(R.drawable.rounded_button_green);
                            buttonLED.setBackgroundResource(R.drawable.rounded_button_green);

                            if(value >= (maxBasic+maxSensor+maxLed+maxMovement)){
                                progressBarMovement.setProgress(maxMovement);
                                buttonMovement.setBackgroundResource(R.drawable.rounded_button_green);
                            }
                            else
                                progressBarMovement.setProgress(value - maxBasic - maxSensor - maxLed);
                        }
                        else{
                            progressBarLed.setProgress(value - maxBasic - maxSensor);
                            buttonMovement.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ExpShow();
                                }
                            });
                            nav_item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    ExpShow();
                                    return false;
                                }
                            });
                        }

                    }
                    else{
                        progressBarSensor.setProgress(value - maxBasic);
                        buttonLED.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ExpShow();
                            }
                        });
                        buttonMovement.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ExpShow();
                            }
                        });
                        nav_item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                ExpShow();
                                return false;
                            }
                        });
                        nav_item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                ExpShow();
                                return false;
                            }
                        });
                    }

                }
                else{
                    progressBarBasic.setProgress(value);
                    buttonSensors.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
                    buttonLED.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
                    buttonMovement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
                    nav_item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            ExpShow();
                            return false;
                        }
                    });
                    nav_item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            ExpShow();
                            return false;
                        }
                    });
                    nav_item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            ExpShow();
                            return false;
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void setMaxProgressbar(){
        //set max progressBar
        DatabaseReference progBarBasic = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Number_of_lesson");
        progBarBasic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                progressBarBasic.setMax(max);
                max_exp+=max;
                Log.d("tag", "onDataChange: thuyngocha basic"+ progressBarBasic.getMax());
                Log.d("z1", "onDataChange: max"+ max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar Sensor
        DatabaseReference progBarSensor = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Number_of_lesson");
        progBarSensor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarSensor.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha sensor"+ progressBarSensor.getMax());
                Log.d("z2", "onDataChange: max"+ max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar Led
        DatabaseReference progBarLed = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Number_of_lesson");
        progBarLed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarLed.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha led"+ progressBarLed.getMax());
                Log.d("z3", "onDataChange: max"+ max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar movement
        DatabaseReference progBarMovement = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Number_of_lesson");
        progBarMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarMovement.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha Movement"+ progressBarMovement.getMax());
                Log.d("z4", "onDataChange: max"+ max_exp);
                progressBarExp.setMax(max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    public void loadingProgressBarTotal(){
        Log.d("tag","checkprogressbar: in loanding");
        int t = 0;
        if(mAuth.getCurrentUser() != null) {
            if (t == 0) {
                setMaxProgressbar();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t++;
            }
            if (t == 1)
                setProgressBarMain();
        }
    }
    private void ExpShow()
    {
        Snackbar snackbar = Snackbar
                .make(drawerLayout, "You don't have enough exp to view this lesson ", Snackbar.LENGTH_LONG);
        snackbar.setAction("Get more", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });
        snackbar.show();

    }

}
