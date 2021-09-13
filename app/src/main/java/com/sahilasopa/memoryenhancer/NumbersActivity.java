package com.sahilasopa.memoryenhancer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sahilasopa.memoryenhancer.Handlers.Generator;
import com.sahilasopa.memoryenhancer.databinding.ActivityNumbersBinding;

public class NumbersActivity extends AppCompatActivity {
    ActivityNumbersBinding binding;
    Generator generator;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        generator = new Generator(this);
        word = String.valueOf(generator.generateNumber(999999));
        binding.title.setVisibility(View.VISIBLE);
        binding.generate.setVisibility(View.VISIBLE);
        binding.generate.setOnClickListener(v -> generateScreen());
        binding.button.setOnClickListener(v -> submitScreen());
        binding.textView5.setOnClickListener(v -> generateScreen());
    }

    public void generateScreen() {
        binding.title.setVisibility(View.GONE);
        binding.button.setVisibility(View.GONE);
        binding.otp.setVisibility(View.GONE);
        binding.otp.setText("");
        binding.textView5.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);
        binding.generate.setVisibility(View.GONE);
        binding.word.setVisibility(View.VISIBLE);
        binding.randomTextIs.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.VISIBLE);
        binding.word.setText(String.valueOf(word));
        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.textView.setText(getString(R.string.remember_it).concat(" ".concat(String.valueOf(millisUntilFinished / 1000))));
            }

            public void onFinish() {
                answerScreen();
            }
        }.start();
    }

    public void answerScreen() {
        // Get from the SharedPreferences
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        int numberScore = values.getInt("numbersPlayed", 0);
        numberScore++;

        SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("numbersPlayed", numberScore);

        // Apply the edits!
        editor.apply();
        binding.word.setVisibility(View.GONE);
        binding.textView.setVisibility(View.GONE);
        binding.title.setVisibility(View.GONE);
        binding.randomTextIs.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.VISIBLE);
        binding.textView5.setVisibility(View.VISIBLE);
        binding.otp.setVisibility(View.VISIBLE);
        binding.button.setVisibility(View.VISIBLE);
    }

    public void submitScreen() {
        final String input = binding.otp.getText().toString();
        if (!(input.isEmpty()) && input.equals(word)) {
            // Get from the SharedPreferences
            SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
            int numberScore = values.getInt("numberScore", 0);
            numberScore++;

            SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("numberScore", numberScore);

            // Apply the edits!
            editor.apply();
            Toast.makeText(this, "Great Job", Toast.LENGTH_SHORT).show();
            word = String.valueOf(generator.generateNumber(9999999));
            generateScreen();
        } else {
            Toast.makeText(this, "The Code Is Incorrect, Try Again", Toast.LENGTH_LONG).show();
        }
    }

}