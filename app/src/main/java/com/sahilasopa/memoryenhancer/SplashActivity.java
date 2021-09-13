package com.sahilasopa.memoryenhancer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.UUID;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        final int delayMillis = 3000;
        // Get from the SharedPreferences
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        String uid = values.getString("uid", "none");
        if (uid.equals("none")) {
            UUID uuid = UUID.randomUUID();
            SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
            SharedPreferences.Editor editor = data.edit();
            editor.putString("uid", uuid.toString());

            // Apply the edits!
            editor.apply();
        }
        Log.d("lol", uid);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        new Handler().postDelayed(() -> {
            //This method will be executed once the timer is over
            Intent main = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(main); // switch to main activity
            finish(); // close this activity
        }, delayMillis);
    }
}