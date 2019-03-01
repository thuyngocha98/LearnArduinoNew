package com.hatn.learnarduino.Tol2.Lesson1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.R;

import java.io.ByteArrayOutputStream;

public class Lesson1Quiz extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    String activity_name, code;
    ImageView imageView1, imageView2;
    FloatingActionButton fab1, fab2, fab;
    boolean isFABOpen;
    Button btnCopy, btnCancel;
    TextView textView, textViewfab1, textViewfab2, codeview;
    CardView cardViewfab1, cardViewfab2, cardviewcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1_quiz);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView=findViewById(R.id.L1C_content);
        coordinatorLayout=findViewById(R.id.lesson1_content_layout);



        DatabaseReference Tol2_lesson1_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Description");
        DatabaseReference Tol2_lesson1_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image1");
        DatabaseReference Tol2_lesson1_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image2");
        DatabaseReference Tol2_lesson1_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Name");
        DatabaseReference Tol2_lesson1_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Code");
        Tol2_lesson1_Content.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value is: " + value);

                //textView.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol2_lesson1_Code.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value is: " + value);

                //code=value;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Tol2_lesson1_Name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //activity_name = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Tol2_lesson1_img1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value is: " + value);



                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                imageBytes = Base64.decode(value, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                //imageView1.setImageBitmap(decodedImage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Tol2_lesson1_img2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d(TAG, "Value is: " + value);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                imageBytes = Base64.decode(value, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                //imageView2.setImageBitmap(decodedImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!isFABOpen){
//                    showFABMenu();
//                }else{
//                    closeFABMenu();
//                }
//            }
//        });

//        fab1 = findViewById(R.id.fab1);
//        fab2 = findViewById(R.id.fab2);
//        cardViewfab1=findViewById(R.id.card_view_fab1);
//        cardViewfab2=findViewById(R.id.card_view_fab2);



//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Lesson1Quiz.this, Lesson1Content.class);
//                startActivity(i);
//            }
//        });







    }

//    private void showCustomDialog() {
//        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//        //then we will inflate the custom alert dialog xml that we created
//        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_viewcode, viewGroup, false);
//
//        codeview = dialogView.findViewById(R.id.textview_code);
//        codeview.setText(code);
//        codeview.setMovementMethod(new ScrollingMovementMethod());
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        final AlertDialog alertDialog = builder.create();
//
//        btnCopy = dialogView.findViewById(R.id.buttonCopy);
//        btnCancel = dialogView.findViewById(R.id.buttonCancel);
//
//        btnCopy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("Copied Code", code);
//                clipboard.setPrimaryClip(clip);
//
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Copied successfully ", Snackbar.LENGTH_LONG);
//                snackbar.show();
//
//                closeFABMenu();
//                alertDialog.dismiss();
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Cancelled ", Snackbar.LENGTH_LONG);
//                snackbar.show();
//
//                closeFABMenu();
//                alertDialog.dismiss();
//            }
//        });
//
//
//        alertDialog.show();
//    }
//
//    public void showFABMenu(){
//        isFABOpen=true;
//        fab.setImageResource(R.drawable.cancel);
//        cardViewfab1.setVisibility(View.VISIBLE);
//        cardViewfab2.setVisibility(View.VISIBLE);
//        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        cardViewfab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//        cardViewfab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//
//
//
//    }
//
//    public void closeFABMenu(){
//        isFABOpen=false;
//        fab.setImageResource(R.drawable.ic_menu);
//        cardViewfab1.animate().translationY(0);
//        cardViewfab2.animate().translationY(0);
//        cardViewfab1.setVisibility(View.INVISIBLE);
//        cardViewfab2.setVisibility(View.INVISIBLE);
//        fab1.animate().translationY(0);
//        fab2.animate().translationY(0);
//
//    }

}
