package com.hatn.learnarduino;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int RC_SIGN_IN = 123;
    public FirebaseAuth mAuth;
    public int Experience = 0;
    private Button buttonBasic, buttonSensors, buttonLED, buttonMovement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        }else {
            functionLogin();
        }
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        //logOut();
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //findViewById(R.id.btn_log_out).setOnClickListener(this);

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
            //TODO: log out from here
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public  void functionLogin(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
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
                Toast.makeText(this, "Login With: " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

                readData();

            } else {
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    public void onClick(View v) {
//        if(v.getId() == R.id.btn_log_out){
//            AuthUI.getInstance()
//                    .signOut(this)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        public void onComplete(@NonNull Task<Void> task) {
//                            // ...
//                            if(mAuth.getCurrentUser() != null)
//                                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
//                            else{
//                                Toast.makeText(MainActivity.this, "log out success", Toast.LENGTH_SHORT).show();
//                                functionLogin();
//
//                            }
//
//                        }
//                    });
//
//        }
//    }

    private void logOut(){
        AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // ...
                            if(mAuth.getCurrentUser() != null)
                                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            else{
                                Toast.makeText(MainActivity.this, "log out success", Toast.LENGTH_SHORT).show();
                                functionLogin();

                            }

                        }
                    });
    }

    public void setValueExperience(){
            String user_id = mAuth.getCurrentUser().getUid();
            DatabaseReference current_user_id = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            current_user_id.setValue(0);
    }

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
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
