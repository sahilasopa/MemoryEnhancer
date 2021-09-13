package com.sahilasopa.memoryenhancer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sahilasopa.memoryenhancer.Handlers.Generator;
import com.sahilasopa.memoryenhancer.databinding.ActivitySentencesBinding;

import java.util.Locale;

public class SentencesActivity extends AppCompatActivity {
    ActivitySentencesBinding binding;
    Generator generator;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySentencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        generator = new Generator(this);
        word = generator.generateString();
        binding.title.setVisibility(View.VISIBLE);
        binding.generate.setVisibility(View.VISIBLE);
        binding.generate.setOnClickListener(v -> generateScreen());
        binding.button.setOnClickListener(v -> submitScreen());
        binding.textView5.setOnClickListener(v -> generateScreen());
    }

    public void generateScreen() {
        // Get from the SharedPreferences
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        int sentencesPlayed = values.getInt("sentencesPlayed", 0);
        sentencesPlayed++;

        SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("sentencesPlayed", sentencesPlayed);

        // Apply the edits!
        editor.apply();
        binding.title.setVisibility(View.GONE);
        binding.button.setVisibility(View.GONE);
        binding.editTextTextPersonName.setVisibility(View.GONE);
        binding.editTextTextPersonName.setText("");
        binding.textView5.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);
        binding.generate.setVisibility(View.GONE);
        binding.word.setVisibility(View.VISIBLE);
        binding.randomTextIs.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.VISIBLE);
        binding.word.setText(word);
        new CountDownTimer(10000, 1000) {

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
        int stringPlayed = values.getInt("stringPlayed", 0);
        stringPlayed++;

        SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("stringPlayed", stringPlayed);

        // Apply the edits!
        editor.apply();
        binding.word.setVisibility(View.GONE);
        binding.textView.setVisibility(View.GONE);
        binding.title.setVisibility(View.GONE);
        binding.randomTextIs.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.VISIBLE);
        binding.textView5.setVisibility(View.VISIBLE);
        binding.editTextTextPersonName.setVisibility(View.VISIBLE);
        binding.button.setVisibility(View.VISIBLE);
    }

    public void submitScreen() {
        final String input = binding.editTextTextPersonName.getText().toString();
        if (!(input.isEmpty()) && input.toUpperCase(Locale.ROOT).equals(word)) {
            // Get from the SharedPreferences
            SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
            int stringScore = values.getInt("stringScore", 0);
            stringScore++;

            SharedPreferences data = getApplicationContext().getSharedPreferences("memories", 0);
            SharedPreferences.Editor editor = data.edit();
            editor.putInt("stringScore", stringScore);

            // Apply the edits!
            editor.apply();
            Toast.makeText(this, "Great Job", Toast.LENGTH_SHORT).show();
            word = generator.generateString();
            generateScreen();
        } else {
            Toast.makeText(this, "The Code Is Incorrect, Try Again", Toast.LENGTH_LONG).show();
        }
    }

}