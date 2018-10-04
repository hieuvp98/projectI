package com.example.dell.projectdemo4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imgSplash;
    Animation animationFadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgSplash = findViewById(R.id.imgsplash);
        animationFadeIn = AnimationUtils.loadAnimation(Splash.this,R.anim.fade_in);
        imgSplash.startAnimation(animationFadeIn);
        imgSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(go);
            }
        });
    }
}
