package com.hatn.learnarduino;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hatn.learnarduino.Tol3.Tol3_Lesson_Quiz;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView selected_item = findViewById(R.id.sel_item);
        selected_item.setText("General feedback");
        final MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("General feedback", "Content suggestion", "Need help", "Bug reports", "Other");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selected_item.setText(item);
                Snackbar.make(view, "Selected " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        final TextInputEditText textInputEditText = findViewById(R.id.name_edit_text);

        final CoordinatorLayout coordinatorLayout = findViewById(R.id.feedback_layout);
        MaterialButton button = findViewById(R.id.button_send_feedback);

        final Intent intent=getIntent();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputEditText.getText().toString().equals(""))
                {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Please input your feedback.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"apps.cherala@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Learn Arduino " + selected_item.getText());
                    i.putExtra(Intent.EXTRA_TEXT, "Hello. I'm " + intent.getStringExtra("Name") + ","+ "\n" + textInputEditText.getText());
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    textInputEditText.setText("");
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
