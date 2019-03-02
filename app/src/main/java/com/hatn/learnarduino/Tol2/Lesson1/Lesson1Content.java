package com.hatn.learnarduino.Tol2.Lesson1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hatn.learnarduino.Function;
import com.hatn.learnarduino.R;

import java.io.ByteArrayOutputStream;

public class Lesson1Content extends AppCompatActivity {

    private static final String TAG = "Leson1Content_log";
    CoordinatorLayout coordinatorLayout;
    String activity_name, code;
    ImageView imageView1, imageView2;
    FloatingActionButton fab1, fab2, fab;
    boolean isFABOpen;
    Button btnCopy, btnCancel;
    TextView textView, textViewfab1, textViewfab2, codeview, codeviewtemp;
    CardView cardViewfab1, cardViewfab2, cardviewcode;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1_content);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView1=findViewById(R.id.L1C_img1);
        imageView2=findViewById(R.id.L1C_img2);
        textView=findViewById(R.id.L1C_content);
        coordinatorLayout=findViewById(R.id.lesson1_content_layout);
        codeviewtemp=findViewById(R.id.textview_codetemp);

        progressDialog=ProgressDialog.show(this,"Loading app data","Please wait for a while",true);


        DatabaseReference Tol2_lesson1_Content = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Description");
        DatabaseReference Tol2_lesson1_img1 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image1");
        DatabaseReference Tol2_lesson1_img2 = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Image2");
        DatabaseReference Tol2_lesson1_Name = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Name");
        DatabaseReference Tol2_lesson1_Code = FirebaseDatabase.getInstance().getReference().child("Type_of_lesson").child("Tol2").child("Lesson1").child("Content").child("Code");
//        Tol2_lesson1_Content.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                String value = dataSnapshot.getValue().toString();
//
//                Log.d(TAG, "Value is: " + value);
//
//                textView.setText(value);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        Function function = new Function();
        function.SetDataIntoObject(Tol2_lesson1_Content, textView);

//        Tol2_lesson1_Code.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                String value = dataSnapshot.getValue().toString();
//
//                Log.d(TAG, "Value is: " + value);
//
//                code=value;
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        function.SetDataIntoObject(Tol2_lesson1_Code,codeviewtemp);
        Tol2_lesson1_Name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                activity_name = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        Tol2_lesson1_img1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                String value = dataSnapshot.getValue().toString();
//
//                Log.d(TAG, "Value is: " + value);
//
//
//
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                byte[] imageBytes = byteArrayOutputStream.toByteArray();
//
//                imageBytes = Base64.decode(value, Base64.DEFAULT);
//                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                imageView1.setImageBitmap(decodedImage);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        function.SetDataIntoObject(Tol2_lesson1_img1, imageView1);



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
                imageView2.setImageBitmap(decodedImage);

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        cardViewfab1=findViewById(R.id.card_view_fab1);
        cardViewfab2=findViewById(R.id.card_view_fab2);


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog=ProgressDialog.show(Lesson1Content.this,"Loading app data","Please wait for a while",true);
                showCustomDialog();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Lesson1Content.this, Lesson1Quiz.class);
                startActivity(i);
            }
        });







    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
//        if(requestCode == 5) {
//
//            // resultCode được set bởi DetailActivity
//            // RESULT_OK chỉ ra rằng kết quả này đã thành công
//            if(resultCode == Activity.RESULT_OK) {
//                // Nhận dữ liệu từ Intent trả về
//                final String result = data.getStringExtra(Lesson1Content.EXTRA_DATA);
//
//                // Sử dụng kết quả result bằng cách hiện Toast
//                Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
//            } else {
//                // DetailActivity không thành công, không có data trả về.
//            }
//        }
//    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);
        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_viewcode, viewGroup, false);

        codeview = dialogView.findViewById(R.id.textview_code);
        codeview.setText(codeviewtemp.getText());
        codeview.setMovementMethod(new ScrollingMovementMethod());

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        btnCopy = dialogView.findViewById(R.id.buttonCopy);
        btnCancel = dialogView.findViewById(R.id.buttonCancel);

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Code", code);
                clipboard.setPrimaryClip(clip);

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Copied successfully ", 50);
                snackbar.show();

                closeFABMenu();
                alertDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Cancelled ", 50);
                snackbar.show();

                closeFABMenu();
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
        progressDialog.dismiss();
    }

    public void showFABMenu(){
        isFABOpen=true;
        fab.setImageResource(R.drawable.cancel);
        cardViewfab1.setVisibility(View.VISIBLE);
        cardViewfab2.setVisibility(View.VISIBLE);
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        cardViewfab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        cardViewfab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));



    }

    public void closeFABMenu(){
        isFABOpen=false;
        fab.setImageResource(R.drawable.ic_menu);
        cardViewfab1.animate().translationY(0);
        cardViewfab2.animate().translationY(0);
        cardViewfab1.setVisibility(View.INVISIBLE);
        cardViewfab2.setVisibility(View.INVISIBLE);
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);

    }
}
