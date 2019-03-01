package com.hatn.learnarduino;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int RC_SIGN_IN = 123;
    public FirebaseAuth mAuth;
    public int Experience = 0;
    private DrawerLayout drawerLayout;
    Button buttonBasic, buttonSensors, buttonLED, buttonMovement;
    ProgressDialog progressDialog;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        //navigation drawer bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //progessOverlay.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);
//        if( isOnline()==false)
//        {
////            Snackbar snackbar = Snackbar
////                    .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", Snackbar.LENGTH_LONG);
////
////            snackbar.show();
//        }

        // login firebaseUI
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            setProfile();
        }else {
            functionLogin();
        }

        //Merge content
        buttonBasic = findViewById(R.id.btn_basic);
        buttonSensors=findViewById(R.id.btn_sensors);
        buttonLED=findViewById(R.id.btn_LED);
        buttonMovement=findViewById(R.id.btn_movement);


        buttonBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Basic.class);
                startActivity(i);
            }
        });

        buttonSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Sensors.class);
                startActivity(i);
            }
        });

        buttonLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LED.class);
                startActivity(i);
            }
        });

        buttonMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Movement.class);
                startActivity(i);
            }
        });


    }

    //Merge content
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_Basic) {
            Intent i = new Intent(this,Basic.class);
            startActivity(i);
        } else if (id == R.id.nav_Sensors) {
            Intent i = new Intent(this,Sensors.class);
            startActivity(i);

        } else if (id == R.id.nav_LED) {
            Intent i = new Intent(this,LED.class);
            startActivity(i);

        } else if (id == R.id.nav_Movement) {
            Intent i = new Intent(this,Movement.class);
            startActivity(i);

        } else if (id == R.id.nav_aboutus) {
            Intent i = new Intent(this, Aboutus.class);
            startActivity(i);
        } else if (id == R.id.nav_moreapps) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Cherala+Apps"));
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_header_name);
        navUsername.setText(name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.tv_header_email);
        navEmail.setText(email);
        final ImageView navImage = (ImageView) headerView.findViewById(R.id.img_header);
        if(!TextUtils.isEmpty(image)){
            Picasso.get().load(image).resize(75, 75).centerCrop().transform(new CropCircleTransformation()).into(navImage);
        }
        else {
            navImage.setImageResource(R.drawable.user_logo);
        }

        progressDialog.dismiss();
        if (isOnline())
        {
            Snackbar snackbar = Snackbar
                    .make(drawerLayout, "Signed in as " +email, Snackbar.LENGTH_LONG)
                    .setAction("LOG OUT", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            logOut();
                        }
                    });
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar
                    .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", Snackbar.LENGTH_LONG);
            snackbar.show();
        }





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
                setProfile();
                readData();

            } else {
                //Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
                if (isOnline())
                {
                    Snackbar snackbar = Snackbar
                            .make(drawerLayout, "Action Failed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else
                {
                    //TODO: snackbar here
                    Snackbar snackbar = Snackbar
                            .make(drawerLayout, "You appeared to be offline, please be online so this app can function normally ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


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
        else {
            Snackbar snackbar = Snackbar
                    .make(drawerLayout, "Can't sign out because you don't have internet connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    // experience user
    public void setValueExperience(){
            String user_id = mAuth.getCurrentUser().getUid();
            DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            current_user_id.setValue(0);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


    // read data user
    public  void readData(){
        String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                if(value != null){
                    Experience = value.intValue();
                }
                else {
                    setValueExperience();
                    Experience = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Get user data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
