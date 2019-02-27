package com.hatn.learnarduino;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgLogo = (ImageView) findViewById(R.id.img_logo);
        final Animation animaTranslate = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
        imgLogo.startAnimation(animaTranslate);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
