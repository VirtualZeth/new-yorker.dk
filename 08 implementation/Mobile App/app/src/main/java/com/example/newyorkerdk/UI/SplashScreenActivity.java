package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.newyorkerdk.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_splash_screen);

        Thread thread = new Thread() {
            public void run() {
                try {
                    // Thread sover i  2 sekunder
                    sleep(2*1000);

                    // efter 2 seconds får vi et andet intent
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);

                    //fjerner activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start runner
        thread.start();
    }
}