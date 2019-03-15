package com.hatn.learnarduino;

import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("General feedback", "Content suggestion", "Need help", "Bug reports", "Other");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Selected " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        final TextInputEditText textInputEditText = findViewById(R.id.name_edit_text);

        final CoordinatorLayout coordinatorLayout = findViewById(R.id.feedback_layout);
        MaterialButton button = findViewById(R.id.button_send_feedback);

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
                    textInputEditText.setText("");
                    //TODO: send to sever
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
//        Intent intent = new Intent(Basic.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        finish();

    }

}
