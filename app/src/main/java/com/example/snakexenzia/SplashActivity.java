package com.example.snakexenzia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay of 2 seconds before transitioning to the GameActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start GameActivity after splash screen
                Intent intent = new Intent(SplashActivity.this, GameActivity.class);
                startActivity(intent);
                finish();  // Finish SplashActivity so the user can't go back to it
            }
        }, 2000); // 2000ms = 2 seconds delay
    }
}
