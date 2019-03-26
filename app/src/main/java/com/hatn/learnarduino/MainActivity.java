package com.hatn.learnarduino;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RewardedVideoAdListener {

    int flag = 0;
    private RewardedVideoAd mRewardedVideoAd;
    private static final int RC_SIGN_IN = 123;
    public FirebaseAuth mAuth;
    int numberTotalContent = 6;
    int max_exp;
    ImageButton buttonBasic, buttonSensors, buttonLED, buttonMovement, buttonTol5, buttonTol6;
    DelayedProgressDialog progressDialog;
    private String email;
    DrawerLayout drawerLayout;
    ProgressBar progressBarSensor, progressBarLed, progressBarBasic, progressBarMovement, progressBarExp;
    LinearLayout linearLayoutBasic, linearLayoutSensors, linearLayoutLED, linearLayoutMovement, linearLayoutTol5, linearLayoutTol6;
    MenuItem nav_item1, nav_item2, nav_item3, nav_item4;
    TextView tvCheckWelcome, tokenTextView;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (isOnline()) {
            setContentView(R.layout.activity_main);
            progressBarSensor = findViewById(R.id.progressBarSensors);
            progressBarBasic = findViewById(R.id.progressBarBasic);
            progressBarLed = findViewById(R.id.progressBarLED);
            progressBarMovement = findViewById(R.id.progressBarMovement);
            tvCheckWelcome = findViewById(R.id.tv_temp_check_welcome);
            drawerLayout = findViewById(R.id.drawer_layout);


            MobileAds.initialize(this, "ca-app-pub-1398912587505329~4968336940");

            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(MainActivity.this);
            mRewardedVideoAd.setRewardedVideoAdListener(this);

            loadRewardedVideoAd();


            //navigation drawer bar
            Toolbar toolbar = findViewById(R.id.toolbar);
            //progessOverlay.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            // Create layout with number of type of lesson
            DatabaseReference number1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Number_Of_Type_Of_Lesson");
            number1.keepSynced(true);
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

                    for (int i = value; i < numberTotalContent; i++) {
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
            if (mAuth.getCurrentUser() != null) {
                setProfile();
                loadingProgressBarTotal();
                //load screen welcome
//            Intent intent = new Intent(MainActivity.this, Welcome.class);
//            intent.putExtra("TypeofSlider", 2);
//            startActivity(intent);

            } else {
                functionLogin();
            }

//            dailyLogin();


//        loadingProgressBarTotal();

            //Merge content
            buttonBasic = findViewById(R.id.btn_basic);
            buttonSensors = findViewById(R.id.btn_sensors);
            buttonLED = findViewById(R.id.btn_LED);
            buttonMovement = findViewById(R.id.btn_movement);
            buttonTol5 = findViewById(R.id.btn_tol5);
            buttonTol6 = findViewById(R.id.btn_tol6);

            linearLayoutBasic = findViewById(R.id.linear_btn1);
            linearLayoutSensors = findViewById(R.id.linear_btn2);
            linearLayoutLED = findViewById(R.id.linear_btn3);
            linearLayoutMovement = findViewById(R.id.linear_btn4);
            linearLayoutTol5 = findViewById(R.id.linear_btn5);
            linearLayoutTol6 = findViewById(R.id.linear_btn6);


            linearLayoutBasic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Basic.class);
                    startActivity(i);
                }
            });
            buttonBasic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Basic.class);
                    startActivity(i);
                }
            });

            linearLayoutTol5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Tol5.class);
                    startActivity(i);
                }
            });


            linearLayoutTol6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Tol6.class);
                    startActivity(i);
                }
            });

            RemoveAd();
//
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry :(")
                    .setMessage("You appeared to be offline, please be online so this app can function normally")
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
    public void onRewarded(RewardItem reward) {
        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Exp");
        current_user_id.keepSynced(true);
        current_user_id.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                current_user_id.setValue(value.intValue() + 5);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        final DatabaseReference current_user_id_token = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
        current_user_id_token.keepSynced(true);
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
        Toast.makeText(this, "You got 5 exp and 10 tokens", Toast.LENGTH_SHORT).show();
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

    //Merge content
    @Override
    public void onBackPressed() {

        if (isOnline()) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        else {
            System.exit(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        final MenuItem alertMenuItem = menu.findItem(R.id.activity_main_menu);

        LinearLayout rootView = (LinearLayout) alertMenuItem.getActionView();

        if (mAuth.getCurrentUser()!=null)
        {


            tokenTextView = rootView.findViewById(R.id.menu_item_number);
            String user_id = mAuth.getCurrentUser().getUid();
            final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Token");
            current_user_id.keepSynced(true);
            current_user_id.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Long value = dataSnapshot.getValue(Long.class);

                    if (value!=null) {
                        tokenTextView.setText(value.toString());
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            if (Integer.parseInt(tokenTextView.getText().toString())!=0)
            {
//                setMaxProgressbar();
//                if (flag==1) {
//                    setProgressBarMain();
//                }
                loadingProgressBarTotal();

            }




        }

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(alertMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu:
                Intent intent = new Intent(MainActivity.this, Gettoken.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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

        drawerLayout.closeDrawer(GravityCompat.START);
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
        String user_id = mAuth.getCurrentUser().getUid();
        final DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Exp");
        current_user_id.keepSynced(true);
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                if (value!=null)
                {
                    progressBarExp.setProgress(value.intValue());
                }
                Log.d("zz", "onDataChange: currentexp " + value);
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
//            progressDialog= ProgressDialog.show(MainActivity.this,"Loading app data","Please wait for a while",true);
//            Alerter.create(MainActivity.this)
//                    .setTitle("Loading...")
//                    .setText("Updating content and lesson")
//                    .setIcon(R.drawable.ic_loading)
//                    .enableProgress(true)
//                    .setProgressColorRes(R.color.lime)
//                    .setDuration(1000)
//                    .setBackgroundColorRes(R.color.alert_background) // or setBackgroundColorInt(Color.CYAN)
//                    .show();


            progressDialog = new DelayedProgressDialog();
            progressDialog.show(getSupportFragmentManager(), "tag");
            Snackbar snackbarz = Snackbar
                    .make(drawerLayout, "Signed in as " + email, 1200)
                    .setAction("LOG OUT", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            logOut();
                        }
                    });
            snackbarz.show();
            enableViews(drawerLayout, true);
//            if (nDialog!=null) {
//                nDialog.dismiss();
//            }


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
        Log.d("zzztest", "test3  setpro");
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
                Log.d("xxxx", "onResult"+resultCode);
                setProfile();
                readData();

            } else {
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
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
            max_exp=0;
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

    // set exp, token, pro version and last login for new user;
    public void setValueExperience(){

        tvCheckWelcome.setText("0");

        String user_id1 = mAuth.getCurrentUser().getUid();

        DatabaseReference current_user_id1 = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Exp");
        current_user_id1.keepSynced(true);
        current_user_id1.setValue(0);
        DatabaseReference token_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Token");
        token_user.keepSynced(true);
        token_user.setValue(10);
        DatabaseReference pro_version = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Pro");
        pro_version.keepSynced(true);
        pro_version.setValue(0);

        final Date date = new Date();
        final Date newDate = new Date(date.getTime());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        final String stringdate = dt.format(newDate);

        DatabaseReference last_login = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("LastLogin");
        last_login.keepSynced(true);
        last_login.setValue(stringdate);

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
        current_user_id2.keepSynced(true);
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
        Log.d("zzztest", "test7  setprogress");
        //set progress progressbar
       invalidateOptionsMenu();
        RemoveAd();
        String progressbar_user_id = mAuth.getCurrentUser().getUid();
        Log.d("tag","checkprogressbar: uid " + progressbar_user_id);
        DatabaseReference progressbar_user = FirebaseDatabase.getInstance().getReference().child("Users").child(progressbar_user_id).child("Exp");
        progressbar_user.keepSynced(true);
        progressbar_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("tag","checkprogressbar: get value");
                int value = 0;
                if (dataSnapshot.getValue()!=null)
                {
                    value=Integer.parseInt(dataSnapshot.getValue().toString());
                }

                progressBarBasic.setProgress(0);
                progressBarSensor.setProgress(0);
                progressBarLed.setProgress(0);
                progressBarMovement.setProgress(0);

                buttonBasic.setBackgroundResource(R.drawable.rounded_button);
                buttonSensors.setBackgroundResource(R.drawable.rounded_button);
                buttonLED.setBackgroundResource(R.drawable.rounded_button);
                buttonMovement.setBackgroundResource(R.drawable.rounded_button);

                int maxBasic = progressBarBasic.getMax();
                int maxSensor = progressBarSensor.getMax();
                int maxLed = progressBarLed.getMax();
                int maxMovement = progressBarMovement.getMax();
                progressBarExp.setMax(maxBasic+maxSensor+maxMovement+maxLed);
//                progressBarBasic.setVisibility(View.VISIBLE);
//                progressBarSensor.setVisibility(View.VISIBLE);
//                progressBarLed.setVisibility(View.VISIBLE);
//                progressBarMovement.setVisibility(View.VISIBLE);



                if(value >= maxBasic){
                    // set click button sensor when exp reasonable
                    linearLayoutSensors.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this,Sensors.class);
                            i.putExtra("MAXBASIC2", progressBarBasic.getMax());
                            startActivity(i);
                        }
                    });
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
                    linearLayoutSensors.setEnabled(true);
                    buttonSensors.setEnabled(true);
                    buttonBasic.setBackgroundResource(R.drawable.rounded_button_green);

                    if(value >= (maxBasic+maxSensor)){
                        // set click button led when exp reasonable
                        linearLayoutLED.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(MainActivity.this,LED.class);
                                i.putExtra("MAXSENSOR2", (progressBarBasic.getMax()+progressBarSensor.getMax()));
                                startActivity(i);
                            }
                        });
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
                        linearLayoutLED.setEnabled(true);
                        buttonLED.setEnabled(true);
                        buttonBasic.setBackgroundResource(R.drawable.rounded_button_green);
                        buttonSensors.setBackgroundResource(R.drawable.rounded_button_green);

                        if(value >= (maxBasic+maxSensor+maxLed)){
                            // set click button movement when exp reasonable
                            linearLayoutMovement.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(MainActivity.this,Movement.class);
                                    i.putExtra("MAXLED2", (progressBarLed.getMax()+progressBarBasic.getMax()+progressBarSensor.getMax()));
                                    startActivity(i);
                                }
                            });
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
                            linearLayoutMovement.setEnabled(true);
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
                            linearLayoutMovement.setOnClickListener(new View.OnClickListener() {
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
                        linearLayoutLED.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ExpShow();
                            }
                        });
                        linearLayoutMovement.setOnClickListener(new View.OnClickListener() {
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
                    linearLayoutSensors.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
                    linearLayoutLED.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
                    linearLayoutMovement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ExpShow();
                        }
                    });
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

                progressDialog.cancel();

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


//        nDialog.dismiss();
    }

    public void setMaxProgressbar(){
        //set max progressBar
        DatabaseReference progBarBasic = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol1").child("Number_of_lesson");
        progBarBasic.keepSynced(true);
        progBarBasic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                progressBarBasic.setMax(max);
                max_exp+=max;
                Log.d("tag", "onDataChange: thuyngocha basic"+ progressBarBasic.getMax());
                Log.d("z1", "onDataChange: max "+ max_exp);
//                progressBarExp.setMax(max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar Sensor
        DatabaseReference progBarSensor = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Number_of_lesson");
        progBarSensor.keepSynced(true);
        progBarSensor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarSensor.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha sensor"+ progressBarSensor.getMax());
                Log.d("z2", "onDataChange: max "+ max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar Led
        DatabaseReference progBarLed = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol3").child("Number_of_lesson");
        progBarLed.keepSynced(true);
        progBarLed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarLed.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha led"+ progressBarLed.getMax());
                Log.d("z3", "onDataChange: max "+ max_exp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //set max progressBar movement
        DatabaseReference progBarMovement = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol4").child("Number_of_lesson");
        progBarMovement.keepSynced(true);
        progBarMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());
                int max = value*5;
                max_exp+=max;
                progressBarMovement.setMax(max);
                Log.d("tag", "onDataChange: thuyngocha Movement"+ progressBarMovement.getMax());
                Log.d("z4", "onDataChange: max "+ max_exp);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        flag=1;
    }



    public void loadingProgressBarTotal(){
        Log.d("tag","checkprogressbar: in loanding");
        int t = 0;
        if(mAuth.getCurrentUser() != null) {
            if (t == 0) {
                setMaxProgressbar();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t++;
            }
            if (t == 1) {
                setProgressBarMain();
//                if (nDialog!=null) {
//                    nDialog.dismiss();
//                }
            }
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
    private void RemoveAd()
    {
        final TextView proversion = new TextView(this);
        proversion.setText("0");
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null)
        {
            String user_id1= mAuth.getCurrentUser().getUid();
            final DatabaseReference pro_version_check = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id1).child("Pro");
            pro_version_check.keepSynced(true);
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
//    invalidateOptionsMenu();
    }



}
