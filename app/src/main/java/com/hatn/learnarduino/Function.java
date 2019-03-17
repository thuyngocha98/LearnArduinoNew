package com.hatn.learnarduino;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;

import java.io.ByteArrayOutputStream;

public class Function {


    public void SetDataIntoObject(DatabaseReference db, final TextView textView)
    {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                Log.d("Lesson 12 Quiz", "Value is: " + value);

                textView.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void SetDataIntoObject(DatabaseReference db, final ImageView imageView)
    {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue().toString();

                //Log.d(TAG, "Value is: " + value);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                imageBytes = Base64.decode(value, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView.setImageBitmap(decodedImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void SetDataIntoObject(DatabaseReference db, final int int1)
    {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                int value = Integer.parseInt(dataSnapshot.getValue().toString());

                //Log.d(TAG, "Value is: " + value);

                //int1= value;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void CheckAnswer(final CardView cardView_answer, final CardView cardView_rightanswer, final TextView textView_answertext, final TextView textView_rightanswer, final CardView answer2, final CardView answer3, final CoordinatorLayout coordinatorLayout){
        cardView_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textView_answertext.getText().toString().equals(textView_rightanswer.getText().toString()))
                {
                    RightAnswer(cardView_rightanswer, coordinatorLayout);
                    cardView_answer.setClickable(false);
                    answer2.setClickable(false);
                    answer3.setClickable(false);


                }
                else WrongAnswer(cardView_rightanswer, coordinatorLayout);
            }
        });
    }
    private void RightAnswer(final CardView cardView, CoordinatorLayout coordinatorLayout){

        cardView.setCardBackgroundColor(Color.parseColor("#ff669900"));
        cardView.setVisibility(View.VISIBLE);
//        cardView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                cardView.setVisibility(View.INVISIBLE);
//            }
//        }, 3000);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Congrats! Right answer ", 500);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#ff669900"));
        snackbar.show();



        //cardView.setVisibility(View.INVISIBLE);

    }
    private void WrongAnswer(final CardView cardView, CoordinatorLayout coordinatorLayout){
        cardView.setCardBackgroundColor(Color.parseColor("#ffff4444"));
        cardView.setVisibility(View.VISIBLE);
        cardView.postDelayed(new Runnable() {
            @Override
            public void run() {
                cardView.setVisibility(View.INVISIBLE);
            }
        },1500);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Wrong answer!! Please try again ", 500);
        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#ffa000"));
        snackbar.show();

    }
    public void ShowCongratsAlert(final Activity activity, TextView textView)
    {
        Alerter.create(activity)
                .setTitle("Congratulation!")
                .setText("You have achieved "+textView.getText().toString())
                .setProgressColorRes(R.color.colorAccent)
                .setDuration(5000)
                .enableSwipeToDismiss()
                .enableVibration(true)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(activity, Achievements.class);
                        activity.startActivity(i);
                    }
                })
                .setBackgroundColorRes(R.color.right) // or setBackgroundColorInt(Color.CYAN)
                .show();
    }
//    public int CheckRight(CardView cardView1, CardView cardView2, CardView cardView3)
//    {
//        int countemp = 0;
//        if (cardView1.getCardBackgroundColor().toString().equals("#ff669900"))
//        {
//            countemp++;
//        }
//        else if (cardView2.getCardBackgroundColor().toString().equals("#ff669900"))
//        {
//            countemp++;
//        }
//        else if (cardView3.getCardBackgroundColor().toString().equals("#ff669900"))
//        {
//            countemp++;
//        }
//        Log.d("countemp", "CheckRight: "+countemp);
//        return countemp;
//    }

//    public boolean isOnline(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnected();
//    }

}
