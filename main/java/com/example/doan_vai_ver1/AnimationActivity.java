package com.example.doan_vai_ver1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class AnimationActivity extends AppCompatActivity {

    private static int Main2 = 5000;
    ImageView sun,clock,hour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setControl();
        setEven();
    }
    private void setEven() {
        Animation sunRise = AnimationUtils.loadAnimation(this, R.anim.sun_awn);
        sun.startAnimation(sunRise);
        Animation clockTurn = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        clock.startAnimation(clockTurn);
        Animation hourTurn = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
        hour.startAnimation(hourTurn);

    }

    private void setControl() {
        sun =  findViewById(R.id.sun);
        clock = findViewById(R.id.clock);
        hour = findViewById(R.id.hour);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },Main2);
    }
}
